package com.example.simplemarketlist.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentAboutBinding
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment
import com.example.simplemarketlist.ui.home.HomeFragmentArgs


class AboutFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_about

    private lateinit var constraintLayout: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        constraintLayout = view.findViewById(R.id.containerAds)
        showBanner()
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

    private fun isFreeVersion(): Boolean {
        return requireActivity().getPackageName() == "com.example.simplemarketlist.free"
    }
}