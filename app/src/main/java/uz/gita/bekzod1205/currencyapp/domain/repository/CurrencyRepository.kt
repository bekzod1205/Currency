package uz.gita.bekzod1205.currencyapp.domain.repository

import uz.gita.bekzod1205.currencyapp.domain.param.CurrencyData

interface CurrencyRepository {
    fun getCurrency(onSuccess: (List<CurrencyData>) -> Unit, onFailure: (Exception) -> Unit)
}