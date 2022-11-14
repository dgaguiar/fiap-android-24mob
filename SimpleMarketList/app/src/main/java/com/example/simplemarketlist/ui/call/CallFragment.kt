package com.example.simplemarketlist.ui.call

import CustomToast
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.simplemarketlist.MainActivity
import com.example.simplemarketlist.R
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment


class CallFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_call

    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var constraintLayout: ConstraintLayout
    private val requestCall = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
        showBanner()
    }

    private fun setUpView(view: View) {
        editText = view.findViewById(R.id.etNumber)
        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            makePhoneCall()
        }
        constraintLayout = view.findViewById(R.id.containerAds)
    }

    private fun makePhoneCall() {
        val number: String = editText.text.toString()
        if (number.trim { it <= ' ' }.isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireContext() as Activity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    requestCall
                )
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        } else {
            CustomToast.warning((activity as MainActivity), "Enter Phone Number")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == requestCall) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
            } else {
                CustomToast.error((activity as MainActivity), "Permission DENIED")
            }
        }
    }

    private fun showBanner() {
        constraintLayout.visibility = if (isFreeVersion()) View.VISIBLE
        else View.GONE
    }

    private fun isFreeVersion(): Boolean {
        return requireActivity().getPackageName() == "com.example.simplemarketlist.free"
    }
}