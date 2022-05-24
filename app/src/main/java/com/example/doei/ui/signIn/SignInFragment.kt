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
import com.example.doei.domain.models.Account
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
                if (!message.isNullOrBlank()) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }
            }
        }

//        activity?.let {
//            viewModel.handleAccountAdded().observe(it) { accountAdded ->
//                if (accountAdded) {
//                    Toast.makeText(context, "Conta Cadastrado", Toast.LENGTH_LONG).show()
//                    findNavController().popBackStack()
//                } else {
//                    Toast.makeText(context, "Houve um erro no cadastro da conta", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
    }

    fun createAccount(){
        var jsonAccount = pegarInfosAccount()
        viewModel.addAccountToDataBase(jsonAccount)
    }

    fun pegarInfosAccount(): Account {
        var account: Account = Account()
        account.name = ""
        account.age = ""
        account.email = binding.etUsername.text.toString()
        account.photo = ""
        return account
    }

    private fun setClickListeners() {
        binding.btRegister.setOnClickListener {
            createAccount()
            verifySignIn()
        }
    }

    private fun verifySignIn() {
        if (fieldsNotEmpty() && passwordIsEqual()) {
            val userName = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            viewModel.registerUser(userName, password)
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