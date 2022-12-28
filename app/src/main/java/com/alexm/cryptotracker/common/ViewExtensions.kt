package com.alexm.cryptotracker.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.alexm.cryptotracker.R

fun FragmentActivity.openFragment(
    fragmentClass: Class<out Fragment>,
    replaceFragment: Boolean = false,
    bundle: Bundle? = null
){
    if (replaceFragment) {
        supportFragmentManager.popBackStack()
        replaceFragment(
            fragmentClass = fragmentClass,
            tag = fragmentClass.canonicalName,
            backStack = fragmentClass.canonicalName,
            bundle = bundle
        )
    } else {
        addFragment(
            fragmentClass = fragmentClass,
            tag = fragmentClass.canonicalName,
            backStack = fragmentClass.canonicalName,
            bundle = bundle
        )
    }
}

fun FragmentActivity.replaceFragment(
    fragmentClass: Class<out Fragment>,
    tag: String? = null,
    container: Int = R.id.main_container,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE,
    backStack: String? = null,
    bundle: Bundle? = null
){
    val fragment = supportFragmentManager.fragmentFactory.instantiate(
        this.classLoader,
        fragmentClass.name
    )
    fragment.arguments = bundle
    supportFragmentManager.commit(allowStateLoss = true) {
        setTransition(transition)
        replace(container, fragment, tag)
        addToBackStack(backStack)
    }
}

fun FragmentActivity.addFragment(
    fragmentClass: Class<out Fragment>,
    tag: String? = null,
    container: Int = R.id.main_container,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE,
    backStack: String? = null,
    bundle: Bundle? = null
){
    val fragment = supportFragmentManager.fragmentFactory.instantiate(
        this.classLoader,
        fragmentClass.name
    )
    fragment.arguments = bundle
    supportFragmentManager.commit(allowStateLoss = true) {
        setTransition(transition)
        add(container, fragment, tag)
        addToBackStack(backStack)
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}