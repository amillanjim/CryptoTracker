package com.alexm.cryptotracker.presentation.ui.fragment

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
import com.alexm.cryptotracker.databinding.FragmentCoinDetailBinding
import com.alexm.cryptotracker.domain.model.CoinDetail
import com.alexm.cryptotracker.domain.model.Tickers
import com.alexm.cryptotracker.presentation.adapter.TagsAdapter
import com.alexm.cryptotracker.presentation.adapter.TeamMembersAdapter
import com.alexm.cryptotracker.presentation.viewmodel.CoinDetailViewModel
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinDetailFragment @Inject constructor(
    private val glide: RequestManager,
    private val teamMembersAdapter: TeamMembersAdapter,
    private val tagsAdapter: TagsAdapter
): BaseFragment<FragmentCoinDetailBinding>(FragmentCoinDetailBinding::inflate) {

    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var coinId: String

    private var isFirstLoading: Boolean = false

    override fun initView() {
        showShimmer()
        viewModel = ViewModelProvider(requireActivity())[CoinDetailViewModel::class.java]
        coinId = arguments?.getString(Constants.COIN_ID, "btc-bitcoin" ) ?: "btc-bitcoin"
        setUpRecyclerView()
        subscribeToObservers()
        loadInformation()
        binding.refreshLayout.setOnRefreshListener { loadInformation() }
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
                            teamMembersAdapter.submitList(result.data.team)
                            tagsAdapter.submitList(result.data.tags)
                            bindViews(coin = result.data)
                        }
                        hideShimmer()
                    }
                    is Resource.Error -> {
                        hideShimmer()
                        touchActionDelegate?.showErrorDialog()
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
                        touchActionDelegate?.showErrorDialog()
                    }
                    is Resource.Loading -> {
                        if (isFirstLoading) showShimmer()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun bindTickersInfo(ticker: Tickers){
        val currentPrice = ticker.quotes?.USD?.price
        val percentageChange: Double = ticker.quotes?.USD?.percentChange30m ?: 0.0
        binding.tvCoinValue.text = "\$$currentPrice"
        val tvProfit = binding.llProfitContainer.findViewById<MaterialTextView>(R.id.tv_profit)
        if (percentageChange > 0.0) {
            tvProfit.text = "^ $percentageChange"
            tvProfit.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_lime))
            tvProfit.background = requireActivity().getDrawable(R.drawable.bg_green_profit)
        } else {
            tvProfit.text = percentageChange.toString()
            tvProfit.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_rose))
            tvProfit.background = requireActivity().getDrawable(R.drawable.bg_red_profit)
        }
    }

    private fun bindViews(coin: CoinDetail){
        binding.tvCoinDetailName.text = coin.name
        binding.tvCoinDescription.text = coin.description
        binding.toolBar.findViewById<MaterialTextView>(R.id.tv_title).text = coin.name
        setUpGlideImage(
            imageUrl = coin.logo
        )
    }

    private fun loadInformation() {
        viewModel.getCoinDetail(coinId = coinId)
        viewModel.getTickers(coinId = coinId)
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
}