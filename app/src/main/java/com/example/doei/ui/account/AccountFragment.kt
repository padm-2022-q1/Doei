package com.example.doei.ui.account

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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
    lateinit var toggle: ToggleButton
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


        toggle = binding.buttonGenderMale
//        toggle.setButtonDrawable(drawable.ic_male)
//        toggle.setButtonDrawable(drawable.ic_female)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toggle.setButtonDrawable(drawable.ic_male)
                toggle.setBackgroundColor(resources.getColor(R.color.water_green))

            } else {
                toggle.setButtonDrawable(drawable.ic_male_off)
                toggle.setBackgroundColor(resources.getColor(R.color.light_gray))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}