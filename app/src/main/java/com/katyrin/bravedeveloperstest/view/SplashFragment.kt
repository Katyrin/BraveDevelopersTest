package com.katyrin.bravedeveloperstest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.katyrin.bravedeveloperstest.R
import com.katyrin.bravedeveloperstest.databinding.FragmentSplashBinding
import com.katyrin.bravedeveloperstest.utils.setRotateImage

class SplashFragment : Fragment() {

    private var binding: FragmentSplashBinding? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSplashBinding.inflate(inflater, container, false)
        .also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.main_container)
        startAnimation()
    }

    private fun startAnimation() {
        binding?.imageView?.setRotateImage { replaceMapFragment() }
    }

    private fun replaceMapFragment() {

    }

    override fun onDetach() {
        binding = null
        navController = null
        super.onDetach()
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}