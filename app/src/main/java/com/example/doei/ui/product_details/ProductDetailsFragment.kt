package com.example.doei.ui.product_details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.doei.databinding.ProductDetailsFragmentBinding


class ProductDetailsFragment : Fragment() {
    private lateinit var binding: ProductDetailsFragmentBinding
    val viewModel: ProductDetailsViewModel by viewModels()

    data class Details(
        val titulo: String,
        val categoria: String,
        val distancia: String,
        val descricao: String
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.tvPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${binding.tvPhone.text}")
            startActivity(intent)
        }
    }

    private fun initObservers() {
        activity?.let {
            viewModel.product.observe(it) { product ->
                binding.textViewNomeProduto.text = product.name
                binding.textviewCategoriaProduto.text = product.category
                binding.textViewDetalhesProduto.text = product.description
                binding.textViewLocal.text = product.local
                if (product.phone.isNotBlank()) {
                    val content = SpannableString(product.phone)
                    content.setSpan(UnderlineSpan(), 0, content.length, 0)
                    binding.tvPhone.text = content
                }

                Glide.with(it)
                    .load(product.photo)
                    .into(binding.imageViewDoacao)
            }
        }
    }

    fun MockDetails(){
        binding.textviewViewTitle.text = "Geladeira em bom estado"
        binding.textviewCategoriaProduto.text = "Eletrodomésticos"
        binding.textViewDetalhesProduto.text = "Geladeira em bom estado da marca Brastemp, por volta de 6 anos de uso porém está em estado ótimo, com algumas marcas e arranhões, funciona perfeitamente"
    }

}