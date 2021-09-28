package com.katyrin.bravedeveloperstest.utils

import android.animation.Animator
import android.app.Activity
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

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

fun Fragment.toast(resource: Int): Unit =
    Toast.makeText(requireContext(), resource, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: String?): Unit =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun AppCompatImageView.loadPokemonImage(imageUrl: String?): Unit =
    Picasso.get().load(imageUrl).into(this)

fun Fragment.hideKeyboard() {
    view?.let {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}