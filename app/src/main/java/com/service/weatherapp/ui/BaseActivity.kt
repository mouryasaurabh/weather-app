package com.service.weatherapp.ui

import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 *This is baseActivity class for project to support some base level customisation in activity
 */
open class BaseActivity : AppCompatActivity() {

    fun addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }

    fun replaceFragmentWithOutBackStack(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)

            .commit()
    }

    fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String,
        @Nullable backStackStateName: String?
    ) {
        backStackStateName?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit()
        } ?: kotlin.run {
            replaceFragmentWithOutBackStack(containerViewId, fragment, fragmentTag)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount - 1 >= 0) {
            supportFragmentManager.fragments[supportFragmentManager.backStackEntryCount - 1].onResume()
        }
        super.onBackPressed()
    }
}