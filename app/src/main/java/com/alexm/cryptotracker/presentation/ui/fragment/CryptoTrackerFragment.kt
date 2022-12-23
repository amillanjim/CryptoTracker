package com.alexm.cryptotracker.presentation.ui.fragment

import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.base.BaseFragment
import com.alexm.cryptotracker.common.Constants
import com.alexm.cryptotracker.common.Resource
import com.alexm.cryptotracker.common.gone
import com.alexm.cryptotracker.common.visible
import com.alexm.cryptotracker.databinding.FragmentCryptoTrackerBinding
import com.alexm.cryptotracker.presentation.adapter.SavedCoinsAdapter
import com.alexm.cryptotracker.presentation.adapter.TrendingCoinsAdapter
import com.alexm.cryptotracker.presentation.viewmodel.CryptoTrackerViewModel
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class CryptoTrackerFragment @Inject constructor(
    private val glide: RequestManager,
    private val savedCoinsAdapter: SavedCoinsAdapter,
    private val trendingCoinsAdapter: TrendingCoinsAdapter,
): BaseFragment<FragmentCryptoTrackerBinding>(FragmentCryptoTrackerBinding::inflate){

    private lateinit var viewModel: CryptoTrackerViewModel
    private var isFirstLoading: Boolean = false

    override fun initView() {
        showShimmer()
        viewModel = ViewModelProvider(requireActivity())[CryptoTrackerViewModel::class.java]
        setUpRecyclerView()
        subscribeToObservers()
        loadInformation()
        setUpGlideImage()
        binding.refreshLayout.setOnRefreshListener { loadInformation() }
    }

    private fun setUpRecyclerView(){
        binding.shimmerTrendingCoins.rvTrending.apply {
            adapter = trendingCoinsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        binding.shimmerMyCoins.rvMyCoins.apply {
            adapter = savedCoinsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun subscribeToObservers(){
        fragmentJob = lifecycleScope.launchWhenCreated {
            viewModel.tickersState.collectLatest{ result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            trendingCoinsAdapter.submitList(result.data.take(200))
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
            viewModel.savedCoinsState.collectLatest{ result ->
                when (result) {
                    is Resource.Success -> { savedCoinsAdapter.submitList(result.data) }
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

    private fun loadInformation(){
        viewModel.getTickers()
        viewModel.getSavedCoins()
    }

    private fun setUpGlideImage(){
        val profileImage: ImageView = binding.llProfileContainer.findViewById(R.id.iv_coin)
        glide.load(Constants.PROFILE_IMAGE_URL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(profileImage)
    }

    private fun hideShimmer(){
        binding.refreshLayout.isRefreshing = false
        binding.shimmerMyCoins.shimmerCoins.gone()
        binding.shimmerTrendingCoins.shimmerTrendingCoins.gone()
        binding.shimmerMyCoins.rvMyCoins.visible()
        binding.shimmerTrendingCoins.rvTrending.visible()
    }

    private fun showShimmer(){
        binding.refreshLayout.isRefreshing = false
        binding.shimmerMyCoins.shimmerCoins.visible()
        binding.shimmerTrendingCoins.shimmerTrendingCoins.visible()
        binding.shimmerMyCoins.rvMyCoins.gone()
        binding.shimmerTrendingCoins.rvTrending.gone()
    }
}