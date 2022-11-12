package com.example.simplemarketlist.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentHomeBinding
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment

class HomeFragment : BaseAuthFragment() {
    override val layout: Int
        get() = R.layout.fragment_home

    private lateinit var binding: FragmentHomeBinding
    val args: HomeFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        requireContext()
        //Toast.makeText(requireContext(), args.userEmail, Toast.LENGTH_LONG).show()

    }
}