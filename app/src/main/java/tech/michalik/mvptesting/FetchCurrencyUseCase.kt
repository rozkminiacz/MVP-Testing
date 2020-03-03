package tech.michalik.mvptesting

import io.reactivex.Single

interface FetchCurrencyUseCase{
    fun execute(): Single<List<CurrencyModel>>
}