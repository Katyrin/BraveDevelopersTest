package com.katyrin.bravedeveloperstest.utils

import android.animation.Animator
import android.view.View
import android.view.animation.DecelerateInterpolator

private const val ROTATION_ANIMATED_AMOUNT = 1000f
private const val ROTATION_DURATION = 3000L

fun View.setRotateImage(onAnimationEnd: () -> Unit) {
    animate()
        .rotationBy(ROTATION_ANIMATED_AMOUNT)
        .setInterpolator(DecelerateInterpolator())
        .setDuration(ROTATION_DURATION)
        .setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                onAnimationEnd()
            }
        })
}