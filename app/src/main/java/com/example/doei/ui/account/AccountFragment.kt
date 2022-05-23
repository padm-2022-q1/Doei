package com.example.doei.ui.account

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.example.doei.domain.models.Account
import com.example.doei.ui.home.HomeViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var backMenuButton: ImageButton
    private val viewModel: AccountViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.

    companion object {
        fun newInstance() = AccountFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAccountBinding.inflate(inflater, container, false)


        backMenuButton = binding.buttonAccountBackMenu
        backMenuButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_settings)
        }


        onClickSaveInfos()


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()

    }

    private fun onClickSaveInfos(){
        binding.buttonAccountSaveInfo.setOnClickListener {

            updateAccountInfo()
        }
    }

    val PICK_IMAGE = 1
    var fileImage: Uri = Uri.EMPTY //variável para armazenar a URI da imagem escolhida pelo usuário

    //ao receber o resultado da atividade de escolha de imagem
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE) {
            try {
                binding.imageAccountInfo.setImageURI(data?.data)
                fileImage = MediaStore.Images.Media.getContentUri(data?.data.toString())

            } catch (e: Exception) {
                throw e
            }

        }
    }

    fun pegarInfosAccount(): Account{
        var account: Account = Account()
        account.name = binding.editTextNameInfo.text.toString()
        account.age = binding.editTextAgeInfo.text.toString()
        account.email = binding.editTextEmailInfo.text.toString()
        account.imageUrl = fileImage.toString()
        return account
    }


    fun updateAccountInfo(){
        var jsonAccount = pegarInfosAccount()
        var success = viewModel.addAccountInfosToDataBase(jsonAccount)
        if (success) {
            Toast.makeText(context, "Informações atualizadas", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Houve um erro na atualização das informações", Toast.LENGTH_LONG).show()
        }
    }
}