package com.example.doei.ui.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.doei.databinding.SignInFragmentBinding
import com.example.doei.utils.FirebaseUtils.firebaseAuth

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: SignInFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInFragmentBinding.inflate(inflater, container, false)
        setClickListeners()
        return binding.root
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

            activity?.let {
                firebaseAuth.createUserWithEmailAndPassword(userName, password)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            SignInFragmentDirections.actionSignInFragmentToNavigationHome().apply {
                                findNavController().navigate(this)
                            }
                        } else {
                            Toast.makeText(context, "Problema ao autenticar, tente novamente", Toast.LENGTH_LONG).show()
                        }
                    }
            }
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