package com.example.simplemarketlist.ui.about

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.simplemarketlist.R
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment


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
}