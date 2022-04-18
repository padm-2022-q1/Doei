package com.example.doei.ui.product_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.doei.databinding.ProductDetailsFragmentBinding
import com.example.doei.ui.login.LoginViewModel

class ProductDetailsFragment : Fragment() {

    private var _binding: ProductDetailsFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var nomeProduto: TextView
    private lateinit var categoriaProduto: TextView
    private lateinit var distanciaProduto: TextView
    private lateinit var detalhesProduto: TextView

    private lateinit var viewModel: ProductDetailsViewModel

    data class Details(
        val titulo: String,
        val categoria: String,
        val distancia: String,
        val descricao: String
    )

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        nomeProduto = binding.textViewNomeProduto
        categoriaProduto = binding.textviewCategoriaProduto
        distanciaProduto = binding.textViewDistancia
        detalhesProduto = binding.textViewDetalhesProduto

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)

        // TODO: Use the ViewModel
        MockDetails()
    }

    fun MockDetails(){
        nomeProduto.text = "Geladeira em bom estado"
        categoriaProduto.text = "Eletrodomésticos"
        distanciaProduto.text = "5 KM"
        detalhesProduto.text = "Geladeira em bom estado da marca Brastemp, por volta de 6 anos de uso porém está em estado ótimo, com algumas marcas e arranhões, funciona perfeitamente"
    }

}