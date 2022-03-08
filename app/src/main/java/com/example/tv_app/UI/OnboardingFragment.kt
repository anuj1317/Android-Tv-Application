package com.example.tv_app.UI


import android.animation.*
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.leanback.app.OnboardingSupportFragment
import com.example.tv_app.R
/**
Created by Anuj Singh
 */

class OnboardingFragment : OnboardingSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logoResourceId = R.drawable.splash
    }

    private val pageTitles = intArrayOf(
        R.string.onboarding_title_welcome,
        R.string.onboarding_title_design,
        R.string.onboarding_title_simple
    )

    private val pageDescriptions = intArrayOf(
        R.string.onboarding_description_welcome,
        R.string.onboarding_description_design,
        R.string.onboarding_description_simple
    )

    private val pageImages = intArrayOf(
        R.drawable.onboard1,
        R.drawable.onboard2,
        R.drawable.onboard3,
       )


    override fun onFinishFragment() {
        super.onFinishFragment()
        val intent = Intent(context, CatalogActivity::class.java)
        // start your next activity
        startActivity(intent)
    }


    override fun getPageTitle(pageIndex: Int): CharSequence {
        return getString(pageTitles[pageIndex])
    }

    override fun getPageDescription(pageIndex: Int): CharSequence {
        return getString(pageDescriptions[pageIndex])
    }


//    override fun onCreateBackgroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
//      return null
//    }
    override fun onCreateForegroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }

    override fun getPageCount(): Int {
        return pageTitles.size
    }

  //Background
    override fun onCreateBackgroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        val bgView = View(activity)
        bgView.setBackgroundResource(R.drawable.back3)
        return bgView
    }

    //Centre Image
     private lateinit var contentView: ImageView
    override fun onCreateContentView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setPadding(0, 32, 0, 32)
            setImageResource(pageImages[0])
            contentView = this
        }
    }


    override fun onPageChanged(newPage: Int, previousPage: Int) {
        logoResourceId = R.drawable.logo_onboard
        val fadeOut = ObjectAnimator.ofFloat(contentView, View.ALPHA, 1.0f, 0.0f)
            .setDuration(500)
            .apply {
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {

                        contentView.setImageResource(pageImages[newPage])
                    }
                })
            }
        // Create a fade-in animation used to fade in nextPage
        val fadeIn = ObjectAnimator.ofFloat(contentView, View.ALPHA, 0.0f, 1.0f)
            .setDuration(500)
        // Create AnimatorSet with our fade-out and fade-in animators, and start it
        AnimatorSet().apply {
            playSequentially(fadeOut, fadeIn)
            start()
        }
    }



}