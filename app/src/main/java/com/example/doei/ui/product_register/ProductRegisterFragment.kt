package com.example.doei.ui.product_register

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.doei.R
import com.example.doei.databinding.ProductRegisterFragmentBinding
import com.example.doei.domain.models.Product
import com.example.doei.domain.repository.FirebaseDatabaseRepository
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson


class ProductRegisterFragment : Fragment() {


    private var _binding: ProductRegisterFragmentBinding? = null

    private val binding get() = _binding!!


    private lateinit var estado: EditText
    private lateinit var cidade: EditText
    private lateinit var endereco: EditText
    private lateinit var numero: EditText
    private lateinit var complemento: EditText

    private lateinit var titulo: EditText
    private lateinit var categoria: EditText
    private lateinit var detalhes: EditText

    private lateinit var imgViewer: ImageView
    private lateinit var botaoEscolherFoto: Button

    private lateinit var botaoAnunciar: Button

    private lateinit var viewModel: ProductRegisterViewModel


    companion object {
        fun newInstance() = ProductRegisterFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val productRegisterViewModel =
            ViewModelProvider(this).get(ProductRegisterViewModel::class.java)

        _binding = ProductRegisterFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductRegisterViewModel::class.java)

        // TODO: Use the ViewModel
        setListeners()
    }

    fun init(){
        estado = binding.editTextState
        cidade = binding.editTextCity
        endereco = binding.editTextAddress
        numero = binding.editTextBuildingNumber
        complemento = binding.editTextComplement

        titulo = binding.editTextProductTitle
        categoria = binding.editTextProductCategory
        detalhes = binding.editTextProductDetails

        imgViewer = binding.imageViewDonationPic
        botaoEscolherFoto = binding.buttonChoosePhoto

        botaoAnunciar = binding.buttonAnnounceProduct

        var intColor: Int = ResourcesCompat.getColor(getResources(), R.color.water_green, null); //Pega a cor customizada dos resources
        botaoAnunciar.setTextColor(Color.WHITE)
        //botaoAnunciar.setBackgroundColor(botaoAnunciar.context.resources.getColor(R.color.purple_500))
        botaoAnunciar.background.setColorFilter(ContextCompat.getColor(requireContext(), androidx.appcompat.R.color.material_deep_teal_500), PorterDuff.Mode.MULTIPLY)
        //botaoAnunciar.setBackgroundColor(intColor)

        //muda o estilo do botão para o usuário ver que está enabled


        botaoAnunciar.isEnabled = true  //libera o botão para toque

        //binding dos componentes com as variáveis
    }



    fun enableAnnounceButton(){
        botaoAnunciar.setTextColor(Color.WHITE)
        botaoAnunciar.background.setColorFilter(ContextCompat.getColor(requireContext(), androidx.appcompat.R.color.material_deep_teal_500), PorterDuff.Mode.MULTIPLY)
        //muda o estilo do botão para o usuário ver que está enabled

        botaoAnunciar.isEnabled = true  //libera o botão para toque
    }

    fun anunciarProduto(){
        var jsonProduto = pegarInfosProduto()
        val firebaseDatabaseRepository : FirebaseDatabaseRepository = FirebaseDatabaseRepository()

        var success = firebaseDatabaseRepository.addProductToDatabase(jsonProduto)
        if(success){

        }
        else{

        }
    }

    private fun pegarInfosProduto() : Product{
        var produto : Product = Product()
        produto.name = titulo.text.toString()
        produto.local = "${cidade.text.toString()} - ${estado.text.toString()} "
        produto.description = detalhes.text.toString()
        produto.photo = fileImage.toString()

        return produto
        //val gson = Gson()

        //return gson.toJson(produto)
    }

    private fun checarInputs(){
        var check: Boolean = true

        if(cidade.text.equals("") ||
            endereco.text.equals("") ||
            numero.text.equals("") ||
            complemento.text.equals("") ||
            titulo.text.equals("") ||
            categoria.text.equals("") ||
            detalhes.text.equals("")
        ){
            check = false
        }

        if(check){
            enableAnnounceButton()
        }


    }

    val PICK_IMAGE = 1
    lateinit var fileImage : Uri //variável para armazenar a URI da imagem escolhida pelo usuário
    //ao receber o resultado da atividade de escolha de imagem
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE) {
            try{
                imgViewer.setImageURI(data?.data)
                fileImage = MediaStore.Images.Media.getContentUri(data?.data.toString())

            }
            catch(e : Exception){
                throw e
            }

        }
    }

    fun setListeners(){

        botaoAnunciar.setOnClickListener(){
            anunciarProduto()
        } //listener de click no botão

        botaoEscolherFoto.setOnClickListener {
            var intentImagePicker : Intent = Intent()
            intentImagePicker.setType("image/*")
            intentImagePicker.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intentImagePicker, "Select Picture"), PICK_IMAGE)
            //TODO: Usar intents para que o usuário adicione uma foto de sua galeria
        }

        //restante: listeners de change nas EditTexts

        estado.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length == 2) checarInputs()
            }
        })  //TODO: Mudar para um dropdown, enquanto isso, só libera o button caso seja um tamanho igual a dois

        cidade.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) checarInputs()
            }
        })

        endereco.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) checarInputs()
            }
        })

        numero.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) checarInputs()
            }
        })

        titulo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) checarInputs()
            }
        })

        categoria.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) checarInputs()
            }
        })

        detalhes.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) checarInputs()
            }
        })
    }

}