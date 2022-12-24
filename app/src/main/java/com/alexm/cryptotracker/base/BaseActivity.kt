package com.alexm.cryptotracker.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alexm.cryptotracker.presentation.viewfactory.CryptoTrackerFragmentFactory
import com.alexm.cryptotracker.presentation.viewfactory.FragmentFactoryEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    abstract val bindingInflater: (LayoutInflater) -> VB

    protected val binding: VB by lazy { bindingInflater.invoke(layoutInflater) }

    open fun VB.initialize(){}

    @Inject
    lateinit var fragmentFactory: CryptoTrackerFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFactoryFragments()
        super.onCreate(savedInstanceState)
        setUpContentView()
        initView()
    }

    private fun setUpContentView() = setContentView(requireNotNull(binding.root))

    private fun injectFactoryFragments(){
        val entryPoint =
            EntryPointAccessors.fromActivity(
                this,
                FragmentFactoryEntryPoint::class.java
            )

        supportFragmentManager.fragmentFactory = entryPoint.getFragmentFactory()
    }

    open fun initView(){}
}