package com.alexm.cryptotracker.presentation.ui.dialog

import android.animation.Animator
import com.alexm.cryptotracker.R
import com.alexm.cryptotracker.base.BaseDialogFragment
import com.alexm.cryptotracker.databinding.DialogFragmentAnimationBinding

class AnimationDialogFragment: BaseDialogFragment<DialogFragmentAnimationBinding>
    (DialogFragmentAnimationBinding::inflate) {

    override fun initView() {
        binding.animationView.setAnimation(R.raw.splash_loader)
        binding.animationView.addAnimatorListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {
                loadNextFragment()
            }
        })
    }

    private fun loadNextFragment(){
        dismissListener?.openCryptoFragment()
        //dismissListener?.openDetailFragment()
    }
}