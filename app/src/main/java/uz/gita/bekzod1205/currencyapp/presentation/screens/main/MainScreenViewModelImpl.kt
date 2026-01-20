package uz.gita.bekzod1205.currencyapp.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.bekzod1205.currencyapp.domain.param.CurrencyData
import uz.gita.bekzod1205.currencyapp.domain.repository.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(private val repository: CurrencyRepository) : MainScreenViewModel, ViewModel() {
    override val currenciesLiveData = MutableLiveData<List<CurrencyData>>()
    override val errorLiveData = MutableLiveData<String>()


    override fun loadCurrencies() {
        repository.getCurrency(onSuccess = {
            currenciesLiveData.value = it
        }, onFailure = {
            errorLiveData.value = it.message
        })
    }
}
