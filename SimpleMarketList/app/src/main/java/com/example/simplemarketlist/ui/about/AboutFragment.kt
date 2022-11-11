package com.example.simplemarketlist.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentAboutBinding
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment
import com.example.simplemarketlist.ui.home.HomeFragmentArgs


class AboutFragment : BaseAuthFragment() {
    override val layout: Int
        get() = R.layout.fragment_about

    private lateinit var binding: FragmentAboutBinding
    val args: HomeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)


    }
}