package uz.gita.bekzod1205.currencyapp.presentation.screens.convert

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding
import uz.gita.bekzod1205.currencyapp.R
import uz.gita.bekzod1205.currencyapp.databinding.FragmentConverterScreenBinding

@AndroidEntryPoint
class ConverterScreen : Fragment(R.layout.fragment_converter_screen) {
    private val binding by viewBinding(FragmentConverterScreenBinding::bind)
    val args: ConverterScreenArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.etAmount.addTextChangedListener {
            if (it.toString().isEmpty()) {
                binding.etResult.setText("0")
                return@addTextChangedListener
            }
            val res = it.toString().toDouble()*args.data.currency.toDouble()
            binding.etResult.setText(res.toString())
        }
        binding.tvRateInfo.text =
            "1 ${args.data.shortTitle} = ${args.data.currency} UZS"
        binding.changeName.text = args.data.shortTitle
        Glide.with(binding.imgFlag1.context)
            .load("https://flagsapi.com/${args.data.shortTitle.take(2)}/flat/64.png")
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imgFlag1)
        Glide.with(binding.imgFlag2.context)
            .load("https://flagsapi.com/UZ/flat/64.png")
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imgFlag2)
    }
}