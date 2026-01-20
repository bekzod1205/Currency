package uz.gita.bekzod1205.currencyapp.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding
import uz.gita.bekzod1205.currencyapp.R
import uz.gita.bekzod1205.currencyapp.databinding.FragmentMainScreenBinding
import uz.gita.bekzod1205.currencyapp.presentation.adapters.CurrencyAdapter
import uz.gita.bekzod1205.currencyapp.presentation.screens.convert.ConverterScreenArgs
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.getValue

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main_screen) {
    private val binding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadCurrencies()
        viewModel.currenciesLiveData.observe(viewLifecycleOwner) {
            val adapter = CurrencyAdapter(it)
            binding.recyclerView.adapter = adapter
            adapter.setOnItemClickListener {
                findNavController().navigate(
                    MainScreenDirections.actionMainScreenToConverterScreen(
                        it
                    )
                )
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, "${it}", Snackbar.LENGTH_SHORT).show()
        }
        binding.tvDate.text = getCurrentDate()
    }

    fun getCurrentDate(): String {
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return format.format(Date())
    }
}