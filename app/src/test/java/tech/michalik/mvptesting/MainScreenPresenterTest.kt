package tech.michalik.mvptesting

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

class MainScreenPresenterTest {
    private val fakeCurrencyList: List<CurrencyModel> = listOf(
        CurrencyModel(
            name = "Bitcoin",
            symbol = "BTC",
            priceUsd = "9877.96"
        ),
        CurrencyModel(
            name = "Etherum",
            symbol = "ETH",
            priceUsd = "271.57"
        )
    )

    @Test
    fun `GIVEN successful data fetch WHEN screen opens THEN display data`() {
        val view: MainScreenContract.View = mockk(relaxed = true)

        val fetchCurrencyUseCase: FetchCurrencyUseCase = mockk {
            every { execute() } returns Single.just(fakeCurrencyList)
        }

        val presenter = MainScreenPresenter(
            fetchCurrencyUseCase = fetchCurrencyUseCase,
            schedulerProvider = TrampolineTestSchedulers()
        )

        presenter.attach(view)

        verify {
            view.displayData(fakeCurrencyList)
        }
    }

    @Test
    fun `GIVEN unsuccessful data fetch WHEN screen opens THEN display error`() {
        val view: MainScreenContract.View = mockk(relaxed = true)

        val fetchCurrencyUseCase: FetchCurrencyUseCase = mockk {
            every { execute() } returns Single.error(Throwable("error occurred"))
        }

        val presenter = MainScreenPresenter(
            fetchCurrencyUseCase = fetchCurrencyUseCase,
            schedulerProvider = TrampolineTestSchedulers()
        )

        presenter.attach(view)

        verify {
            view.displayError(any())
        }
    }

    @Test
    fun `ON start fetching data IT SHOULD show progress indicator`() {
        val view: MainScreenContract.View = mockk(relaxed = true)

        val fetchCurrencyUseCase: FetchCurrencyUseCase = mockk {
            every { execute() } returns Single.never()
        }

        val presenter = MainScreenPresenter(
            fetchCurrencyUseCase = fetchCurrencyUseCase,
            schedulerProvider = TrampolineTestSchedulers()
        )

        presenter.attach(view)

        verify {
            view.toggleLoadingIndicator(true)
        }
    }

    @Test
    fun `ON fetching data complete IT SHOULD hide progress indicator`() {
        val view: MainScreenContract.View = mockk(relaxed = true)

        val fetchCurrencyUseCase: FetchCurrencyUseCase = mockk {
            every { execute() } returns Single.just(fakeCurrencyList)
        }

        val presenter = MainScreenPresenter(
            fetchCurrencyUseCase = fetchCurrencyUseCase,
            schedulerProvider = TrampolineTestSchedulers()
        )

        presenter.attach(view)

        verify {
            view.toggleLoadingIndicator(false)
        }
    }

    @Test
    fun `ON fetching data error IT SHOULD hide progress indicator`() {
        val view: MainScreenContract.View = mockk(relaxed = true)

        val fetchCurrencyUseCase: FetchCurrencyUseCase = mockk {
            every { execute() } returns Single.error(Throwable("error occurred"))
        }

        val presenter = MainScreenPresenter(
            fetchCurrencyUseCase = fetchCurrencyUseCase,
            schedulerProvider = TrampolineTestSchedulers()
        )

        presenter.attach(view)

        verify {
            view.toggleLoadingIndicator(false)
        }
    }
}