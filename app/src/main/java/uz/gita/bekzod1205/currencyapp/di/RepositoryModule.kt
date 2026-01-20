package uz.gita.bekzod1205.currencyapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.bekzod1205.currencyapp.domain.repository.CurrencyRepository
import uz.gita.bekzod1205.currencyapp.data.repository.CurrencyRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideCurrencyRepository(repository: CurrencyRepositoryImpl): CurrencyRepository
}