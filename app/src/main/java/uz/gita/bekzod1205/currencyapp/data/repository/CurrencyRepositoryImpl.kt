package uz.gita.bekzod1205.currencyapp.data.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.bekzod1205.currencyapp.data.remote.response.CurrencyResponse
import uz.gita.bekzod1205.currencyapp.data.remote.response.toData
import uz.gita.bekzod1205.currencyapp.data.remote.service.CurrencyService
import uz.gita.bekzod1205.currencyapp.domain.param.CurrencyData
import uz.gita.bekzod1205.currencyapp.domain.repository.CurrencyRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyRepository {
    override fun getCurrency(
        onSuccess: (List<CurrencyData>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        currencyService.getCurrency(getCurrentDate()).enqueue(object : Callback<List<CurrencyResponse>> {
            override fun onResponse(
                call: Call<List<CurrencyResponse>?>,
                response: Response<List<CurrencyResponse>?>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()!!.map { it.toData() }
                    onSuccess(list)
                } else {
                    onFailure(Exception("Error"))
                }
            }

            override fun onFailure(
                call: Call<List<CurrencyResponse>?>,
                t: Throwable
            ) {
                onFailure(Exception(t))
            }
        })
    }

    fun getCurrentDate(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(Date())
    }
}