package uz.gita.bekzod1205.currencyapp.domain.param

import java.io.Serializable

data class CurrencyData(
    val shortTitle: String,
    val title: String,
    val currency: String,
    val diff: String
): Serializable
