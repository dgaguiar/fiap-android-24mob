package com.example.simplemarketlist.ui.map

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.simplemarketlist.R
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_maps
    private lateinit var constraintLayout: ConstraintLayout

    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(-23.537429442, -46.7684107199)
        val zoomLevel = 16f

        googleMap.addMarker(MarkerOptions().position(sydney).title("Super mercado mais barato"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraintLayout = view.findViewById(R.id.containerAds)
        showBanner()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

    private fun isFreeVersion(): Boolean {
        return requireActivity().getPackageName() == "com.example.simplemarketlist.free"
    }
}