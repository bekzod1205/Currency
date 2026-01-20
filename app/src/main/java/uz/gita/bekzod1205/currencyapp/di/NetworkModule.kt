package uz.gita.bekzod1205.currencyapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.bekzod1205.currencyapp.data.remote.service.CurrencyService
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @[Provides Singleton]
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit = Retrofit.Builder()
        .baseUrl("https://cbu.uz")
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(ChuckerInterceptor(context))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton]
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService =
        retrofit.create(CurrencyService::class.java)
}