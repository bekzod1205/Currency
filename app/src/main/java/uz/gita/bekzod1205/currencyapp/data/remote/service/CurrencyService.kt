package uz.gita.bekzod1205.currencyapp.data.remote.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import uz.gita.bekzod1205.currencyapp.data.remote.response.CurrencyResponse

interface CurrencyService {

    @GET("/uz/arkhiv-kursov-valyut/json/all/{date}/")
    fun getCurrency(@Path("date") date: String): Call<List<CurrencyResponse>>

}