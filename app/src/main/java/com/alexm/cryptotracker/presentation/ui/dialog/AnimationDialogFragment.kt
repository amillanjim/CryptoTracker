package com.alexm.cryptotracker.presentation.ui.dialog

import android.animation.Animator
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.base.BaseDialogFragment
import com.alexm.cryptotracker.databinding.DialogFragmentAnimationBinding
import com.alexm.cryptotracker.presentation.navigator.CryptoTrackerScreenNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AnimationDialogFragment: BaseDialogFragment<DialogFragmentAnimationBinding>
    (DialogFragmentAnimationBinding::inflate) {

    @Inject
    lateinit var navigator: CryptoTrackerScreenNavigator

    override fun initView() {
        binding.animationView.setAnimation(R.raw.splash_loader)
        binding.animationView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {
                navigator.navigateToCryptoTracker()
            }
        })
    }
}