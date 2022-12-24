package com.alexm.cryptotracker.presentation.ui.fragment

import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.base.BaseFragment
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.gone
import com.alexm.cryptotracker.common.visible
import com.alexm.cryptotracker.data.local.CoinEntity
import com.alexm.cryptotracker.databinding.FragmentCoinDetailBinding
import com.alexm.cryptotracker.domain.model.CoinDetail
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.presentation.adapter.TagsAdapter
import com.alexm.cryptotracker.presentation.adapter.TeamMembersAdapter
import com.alexm.cryptotracker.presentation.viewmodel.CoinDetailViewModel
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinDetailFragment @Inject constructor(private val glide: RequestManager):
    BaseFragment<FragmentCoinDetailBinding>(FragmentCoinDetailBinding::inflate) {

    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var coinId: String
    private lateinit var logo: String
    private lateinit var teamMembersAdapter: TeamMembersAdapter
    private lateinit var tagsAdapter: TagsAdapter
    private lateinit var coinEntity: CoinEntity

    private var isFirstLoading: Boolean = false
    private var isFavorite: Boolean = false

    override fun initView() {
        showShimmer()
        viewModel = ViewModelProvider(requireActivity())[CoinDetailViewModel::class.java]
        coinId = arguments?.getString(Constants.COIN_ID, Constants.DEFAULT_COIN_ID )
            ?: Constants.DEFAULT_COIN_ID
        isFavorite = arguments?.getBoolean(Constants.IS_FAVORITE_COIN) ?: false
        binding.toolBar.findViewById<MaterialCheckBox>(R.id.cb_favorite).isChecked = isFavorite
        initAdapters()
        setUpRecyclerView()
        subscribeToObservers()
        loadInformation()
        binding.refreshLayout.setOnRefreshListener { loadInformation() }
    }

    private fun initAdapters(){
        teamMembersAdapter = TeamMembersAdapter()
        tagsAdapter = TagsAdapter()
    }

    private fun setUpRecyclerView(){
        binding.rvTeamMembers.apply {
            adapter = teamMembersAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        binding.rvTags.apply {
            adapter = tagsAdapter
        }
    }

    private fun subscribeToObservers(){
        fragmentJob = lifecycleScope.launchWhenCreated {
            viewModel.coinDetailState.collect{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            teamMembersAdapter.submitList(it.team)
                            tagsAdapter.submitList(it.tags)
                            bindCoinInfo(coin = it)
                        }
                        hideShimmer()
                    }
                    is Resource.Error -> {
                        hideShimmer()
                        navigator.openErrorDialogFragment()
                    }
                    is Resource.Loading -> {
                        if (isFirstLoading) showShimmer()
                    }
                    else -> {}
                }
            }
        }

        fragmentJob = lifecycleScope.launchWhenCreated {
            viewModel.tickersState.collect{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            bindTickersInfo(ticker = it)
                        }
                        hideShimmer()
                    }
                    is Resource.Error -> {
                        hideShimmer()
                        navigator.openErrorDialogFragment()
                    }
                    is Resource.Loading -> {
                        if (isFirstLoading) showShimmer()
                    }
                    else -> {}
                }
            }
        }

        fragmentJob = lifecycleScope.launchWhenCreated {
            viewModel.savedCoinDetailState.collect{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            coinEntity = it
                        }
                    }
                    else -> {}
                }
            }
        }

        fragmentJob = lifecycleScope.launchWhenCreated {
            viewModel.saveCoinState.collect{ result ->
                when (result) {
                    is Resource.Success -> showSavedCoinSnackBar(isSuccess = true)
                    is Resource.Error -> showSavedCoinSnackBar(isSuccess = false)
                    else -> {}
                }
            }
        }

        fragmentJob = lifecycleScope.launchWhenCreated {
            viewModel.deleteCoinState.collect{ result ->
                when (result) {
                    is Resource.Success -> showDeletedCoinSnackBar(isSuccess = true)
                    is Resource.Error -> showDeletedCoinSnackBar(isSuccess = false)
                    else -> {}
                }
            }
        }
    }

    private fun bindCoinInfo(coin: CoinDetail){
        logo = coin.logo ?: ""
        binding.tvCoinDetailName.text = coin.name
        binding.tvCoinDescription.text = coin.description
        binding.toolBar.findViewById<MaterialTextView>(R.id.tv_title).text = coin.name
        binding.toolBar.findViewById<AppCompatImageButton>(R.id.ib_back).setOnClickListener {
            navigator.popFragment()
        }
        setUpGlideImage(
            imageUrl = coin.logo
        )
    }

    private fun bindTickersInfo(ticker: Tickers){
        val currentPrice = ticker.quotes?.USD?.price
        val percentageChange: Double = ticker.quotes?.USD?.percentChange30m ?: 0.0
        binding.tvCoinValue.text =
            requireActivity().getString(R.string.percentage_change, currentPrice.toString())
        val tvProfit = binding.llProfitContainer.findViewById<MaterialTextView>(R.id.tv_profit)
        if (percentageChange > 0.0) {
            tvProfit.text =
                requireActivity().getString(R.string.percentage_change, percentageChange.toString())
            tvProfit.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_lime))
            tvProfit.background =
                AppCompatResources.getDrawable(requireActivity(),R.drawable.bg_green_profit)
        } else {
            tvProfit.text = percentageChange.toString()
            tvProfit.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_rose))
            tvProfit.background =
                AppCompatResources.getDrawable(requireActivity(), R.drawable.bg_red_profit)
        }
        binding.toolBar.findViewById<MaterialCheckBox>(R.id.cb_favorite).setOnClickListener {
            if (isFavorite) viewModel.deleteCoin(coin = coinEntity)
            else viewModel.saveCoin(tickers = ticker, coinLogo = logo)
        }
    }

    private fun loadInformation() {
        viewModel.getCoinDetail(coinId = coinId)
        viewModel.getTickers(coinId = coinId)
        if (isFavorite) viewModel.getSavedCoinDetail(coinId = coinId)
    }

    private fun setUpGlideImage(imageUrl: String? = Constants.GENERIC_COIN_IMAGE){
        val profileImage = binding.ivCoinDetail
        glide.load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(profileImage)
    }

    private fun hideShimmer(){
        if (viewModel.coinDetailHasFinished.value && viewModel.tickersHasFinished.value) {
            binding.refreshLayout.isRefreshing = false
            binding.shimmerCoinDetail.gone()
            binding.clMainDetail.visible()
        }
    }

    private fun showShimmer(){
        binding.refreshLayout.isRefreshing = false
        binding.shimmerCoinDetail.visible()
        binding.clMainDetail.gone()
    }

    private fun showDeletedCoinSnackBar(isSuccess: Boolean){
        val messageId = if (isSuccess) R.string.coin_deleted
        else R.string.coin_delete_error
        showSnackBar(messageId = messageId)
    }

    private fun showSavedCoinSnackBar(isSuccess: Boolean){
        val messageId = if (isSuccess) R.string.coin_saved
        else R.string.coin_saving_error
        showSnackBar(messageId = messageId)
    }

    private fun showSnackBar(messageId: Int){
        Snackbar.make(binding.clMainDetail, messageId, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.gray_dark_ocean))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_pearl))
            .show()
    }
}