package com.alexm.cryptotracker.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.alexm.cryptotracker.R

fun FragmentActivity.replaceFragment(
    fragment: Class<out Fragment>,
    tag: String? = null,
    container: Int = R.id.main_container,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE,
    backStack: String? = null,
    bundle: Bundle? = null
){
    supportFragmentManager.commit(allowStateLoss = true) {
        setTransition(transition)
        replace(container, fragment, bundle, tag)
        addToBackStack(backStack)
    }
}

fun FragmentActivity.addFragment(
    fragment: Class<out Fragment>,
    tag: String? = null,
    container: Int = R.id.main_container,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE,
    backStack: String? = null,
    bundle: Bundle? = null
){
    supportFragmentManager.commit(allowStateLoss = true) {
        setTransition(transition)
        add(container, fragment, bundle, tag)
        addToBackStack(backStack)
    }
}

fun FragmentActivity.openFragment(
    fragmentClass: Class<out Fragment>,
    replaceFragment: Boolean = false,
    bundle: Bundle? = null
){
    if (replaceFragment) {
        supportFragmentManager.popBackStack()
        replaceFragment(
            fragment = fragmentClass,
            tag = fragmentClass.canonicalName,
            backStack = fragmentClass.canonicalName,
            bundle = bundle
        )
    } else {
        addFragment(
            fragment = fragmentClass,
            tag = fragmentClass.canonicalName,
            backStack = fragmentClass.canonicalName,
            bundle = bundle
        )
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