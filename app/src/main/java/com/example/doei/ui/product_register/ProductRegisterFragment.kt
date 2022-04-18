package com.example.doei.ui.product_register

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.doei.R
import com.example.doei.databinding.ProductRegisterFragmentBinding
import com.example.doei.ui.login.LoginViewModel


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
            ViewModelProvider(this).get(LoginViewModel::class.java)

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

        //binding dos componentes com as variáveis
    }



    fun enableAnnounceButton(){
        var intColor: Int = ResourcesCompat.getColor(getResources(), R.color.water_green, null); //Pega a cor customizada dos resources
        botaoAnunciar.setTextColor(Color.WHITE)
        botaoAnunciar.setBackgroundColor(intColor)
        //muda o estilo do botão para o usuário ver que está enabled


        botaoAnunciar.isEnabled = true  //libera o botão para toque
    }

    fun anunciarProduto(){
        //TODO: integrar com backend
    }

    fun checarInputs(){
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

    fun setListeners(){

        botaoAnunciar.setOnClickListener(){
            anunciarProduto()
        } //listener de click no botão

        botaoEscolherFoto.setOnClickListener {
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