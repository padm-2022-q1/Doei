package com.example.doei.ui.account

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.doei.MainActivity
import com.example.doei.R
import com.example.doei.R.*
import com.example.doei.databinding.FragmentHomeBinding
import com.example.doei.databinding.FragmentAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private lateinit var backMenuButton: ImageButton
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var maleButton: ImageButton
    lateinit var femaleButton: ImageButton
    lateinit var upload: TextView
    lateinit var main: MainActivity
    private val viewModel: AccountViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        backMenuButton = binding.buttonAccountBackMenu
        backMenuButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_settings)
        }

        maleButton = binding.buttonGenderMale

        onClickSaveInfos()
//        maleButton.setOnClickListener {
//            maleButton.setBackgroundColor(Color.GREEN)
//        }
//        toggle.setButtonDrawable(drawable.ic_male)
//        toggle.setButtonDrawable(drawable.ic_female)
//        toggle.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//
//                toggle.setButtonDrawable(drawable.ic_male)
//                toggle.setPadding(3,3,3,3)
//                toggle.setBackgroundColor(resources.getColor(R.color.water_green))
//
//            } else {
//                toggle.setButtonDrawable(drawable.ic_male_off)
//                toggle.setBackgroundColor(resources.getColor(R.color.light_gray))
//            }
//        }

//        upload = binding.textUploadImage
//        upload.setOnClickListener { Toast.makeText(main,
//            R.string.text_on_click, Toast.LENGTH_LONG).show() }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickSaveInfos(){
        binding.buttonAccountSaveInfo.setOnClickListener {

            val name = binding.editTextNameInfo.text.toString().trim()
            val idade = binding.editTextAgeInfo.text.toString().trim()
            val email = binding.editTextEmailInfo.text.toString().trim()

            viewModel.saveAccountInfos(name, idade, email)
        }
    }
}