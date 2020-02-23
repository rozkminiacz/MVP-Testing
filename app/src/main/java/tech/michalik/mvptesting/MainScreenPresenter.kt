package tech.michalik.mvptesting

import io.reactivex.rxkotlin.subscribeBy

class MainScreenPresenter(
    private val fetchCurrencyUseCase: FetchCurrencyUseCase,
    private val schedulerProvider: SchedulerProvider
) : MainScreenContract.Presenter {
    private var view: MainScreenContract.View? = null

    override fun attach(view: MainScreenContract.View) {
        this.view = view
        fetchData()
    }

    private fun fetchData() {
        fetchCurrencyUseCase.execute()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.main)
            .doOnSubscribe { view?.toggleLoadingIndicator(loading = true) }
            .doFinally { view?.toggleLoadingIndicator(loading = false) }
            .subscribeBy(
                onError = {
                    view?.displayError(it)
                },
                onSuccess = {
                    view?.displayData(it)
                }
            )
    }

    override fun detach() {
        this.view = null
    }
}