package com.example.simplemarketlist.ui.shared

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.simplemarketlist.R
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment


class SahredFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_sahred

    lateinit var sharedButton: Button
    lateinit var textShare: TextView
    private lateinit var constraintLayout: ConstraintLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        showBanner()
    }

    private fun setUpView(view: View) {
        textShare = view.findViewById(R.id.shareText)
        sharedButton = view.findViewById(R.id.btnShare)
        sharedButton.setOnClickListener {
            val s = textShare.text.toString()
            sendMessage(s)
        }
        constraintLayout = view.findViewById(R.id.containerAds)
    }

    private fun sendMessage(message: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)

        startActivity(shareIntent)
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

}