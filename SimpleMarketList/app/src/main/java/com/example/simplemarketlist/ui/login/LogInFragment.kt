package com.example.simplemarketlist.ui.login

import BaseFragment
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.simplemarketlist.R
import com.example.simplemarketlist.databinding.FragmentLogInBinding
import com.example.simplemarketlist.models.RequestState
import com.example.simplemarketlist.ui.base.auth.NAVIGATION_KEY

class LogInFragment : BaseFragment() {
    override val layout = R.layout.fragment_log_in

    private lateinit var tvSubTitleSignUp: TextView
    private lateinit var containerLogin: LinearLayout
    private lateinit var tvResetPassword: TextView
    private lateinit var tvNewAccount: TextView

    private lateinit var btLogin: Button
    private lateinit var etEmailLogin: EditText
    private lateinit var etPasswordLogin: EditText

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLogInBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideToolBar()
        setUpView(view)
        registerObserver()
        registerBackPressedAction()
    }

    private fun registerBackPressedAction() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun registerObserver() {
        loginViewModel.loginState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> showSuccess()
                is RequestState.Error -> showError(it.throwable)
                is RequestState.Loading -> showLoading("Realizando a autentica????o")
            }
        })

        loginViewModel.resetPasswordState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage(it.data)
                }
                is RequestState.Error -> showError(it.throwable)
                is RequestState.Loading -> showLoading("Reenviando o e-mail para troca de senha ")
            }
        })
    }

    private fun showSuccess() {
        hideLoading()
        showToolBar()
        val navIdForArguments = arguments?.getInt(NAVIGATION_KEY)
        if (navIdForArguments == null) {
            findNavController().navigate(R.id.main_nav_graph)
        } else {
            findNavController().popBackStack(navIdForArguments, false)
        }
    }

    private fun showError(throwable: Throwable) {
        hideLoading()
        showMessage(throwable.message)
    }

    private fun setUpView(view: View) {
        tvSubTitleSignUp = view.findViewById(R.id.tvSubTitleLogin)
        containerLogin = view.findViewById(R.id.containerLogin)
        tvResetPassword = view.findViewById(R.id.tvResetPassword)
        tvNewAccount = view.findViewById(R.id.tvNewAccount)
        btLogin = view.findViewById(R.id.btLogin)
        etEmailLogin = view.findViewById(R.id.etEmailLogin)
        etPasswordLogin = view.findViewById(R.id.etPasswordLogin)

        btLogin.setOnClickListener {
            loginViewModel.signIn(
                etEmailLogin.text.toString(),
                etPasswordLogin.text.toString()
            )
        }

        tvResetPassword.setOnClickListener {
            loginViewModel.resetPassword(etEmailLogin.text.toString())
        }

        tvNewAccount.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }
}