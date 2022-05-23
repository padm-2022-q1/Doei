package com.example.doei.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doei.databinding.FragmentLoginBinding
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setClickListeners()
        initObservers()

        return root
    }

    override fun onStart() {
        super.onStart()
        binding.vGreen.visibility = View.VISIBLE
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun initObservers() {
        viewModel.getCurrentUser().observe(requireActivity()) { firebaseUser ->
            if (firebaseUser != null) {
                LoginFragmentDirections.actionNavigationLoginToNavigationHome().run {
                    findNavController().navigate(actionId)
                }
            }else {
                binding.vGreen.visibility = View.INVISIBLE
                binding.pbLoading.visibility = View.INVISIBLE
            }
        }

        viewModel.handleAuthError().observe(requireActivity()) { message ->
            if (!message.isNullOrBlank()) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun setClickListeners() {
        binding.btLogin.setOnClickListener(this)
        binding.btSignin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btLogin.id -> {
                val email = binding.etUsername.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.loginClicked(email, password)
                }
            }

            binding.btSignin.id -> {
                LoginFragmentDirections.actionNavigationLoginToSignInFragment().run {
                    findNavController().navigate(actionId)
                }
            }
        }
    }
}