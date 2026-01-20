package uz.gita.bekzod1205.currencyapp.presentation.screens.main

import androidx.lifecycle.LiveData
import uz.gita.bekzod1205.currencyapp.domain.param.CurrencyData

interface MainScreenViewModel {
    val currenciesLiveData: LiveData<List<CurrencyData>>
    val errorLiveData: LiveData<String>


    fun loadCurrencies()
}