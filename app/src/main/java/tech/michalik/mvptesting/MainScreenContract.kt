package tech.michalik.mvptesting

interface MainScreenContract {
    interface View {
        fun displayData(list: List<CurrencyModel>)
        fun toggleLoadingIndicator(loading: Boolean)
        fun displayError(throwable: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
    }
}