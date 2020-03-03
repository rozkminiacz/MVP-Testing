package tech.michalik.mvptesting

import io.reactivex.Single

class RemoteFetchCurrencyUseCase(private val networkService: NetworkService):
    FetchCurrencyUseCase {
    override fun execute(): Single<List<CurrencyModel>> {
        return networkService.getTickers().map { it.data }
    }
}