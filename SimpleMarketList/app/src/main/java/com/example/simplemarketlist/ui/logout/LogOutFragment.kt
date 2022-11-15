package com.example.simplemarketlist.ui.logout

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.simplemarketlist.R
import com.example.simplemarketlist.ui.base.auth.BaseAuthFragment
import com.google.firebase.auth.FirebaseAuth


class LogOutFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_log_out

    private var mAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth.signOut()
        NavHostFragment.findNavController(this).navigate(R.id.main_nav_graph)
    }
}