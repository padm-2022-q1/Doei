package com.example.doei.ui.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doei.databinding.SignInFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: SignInFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInFragmentBinding.inflate(inflater, container, false)
        setClickListeners()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        activity?.let {
            viewModel.handleCurrentUser().observe(it) { user ->
                if (user != null) {
                    SignInFragmentDirections.actionSignInFragmentToNavigationHome().run {
                        findNavController().navigate(actionId)
                    }
                }
            }

            viewModel.handleAuthError().observe(it) { message ->
                    Toast.makeText(context, "Erro no cadastro", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setClickListeners() {
        binding.btRegister.setOnClickListener {
            verifySignIn()
        }
    }

    private fun verifySignIn() {
        if (fieldsNotEmpty() && passwordIsEqual()) {
            val userName = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            viewModel.registerUser(userName, password)
        } else {
            Toast.makeText(activity, "Senhas nao estao iguais", Toast.LENGTH_LONG).show()
        }
    }

    private fun passwordIsEqual(): Boolean {
        return binding.etPassword.text.toString() == binding.etPasswordConfirm.text.toString()
    }

    private fun fieldsNotEmpty(): Boolean {
        return !(binding.etUsername.text.isNullOrBlank() ||
                binding.etPassword.text.isNullOrBlank() ||
                binding.etPasswordConfirm.text.isNullOrBlank())
    }

}