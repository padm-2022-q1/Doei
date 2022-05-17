package com.example.doei.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doei.R
import com.example.doei.databinding.FragmentLoginBinding
import com.example.doei.utils.FirebaseUtils.firebaseAuth
import com.example.doei.utils.FirebaseUtils.firebaseUser


class LoginFragment : Fragment(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setClickListeners()

        return root
    }

    override fun onStart() {
        super.onStart()
        if (firebaseUser != null) {
            findNavController().navigate(R.id.navigation_home)
        }
    }

    private fun setClickListeners() {
        binding.btLogin.setOnClickListener(this)
        binding.btSignin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btLogin.id -> {
                activity?.let {
                    firebaseAuth.signInWithEmailAndPassword(
                        binding.etUsername.text.toString(),
                        binding.etPassword.text.toString()
                    ).addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            LoginFragmentDirections.actionNavigationLoginToNavigationHome().run {
                                findNavController().navigate(actionId)
                            }
                        } else {
                            Toast.makeText(
                                context, "Falha na autenticação",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
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