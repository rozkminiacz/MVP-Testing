package tech.michalik.mvptesting

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainScreenContract.View {

    private val presenter: MainScreenContract.Presenter by lazy {
        MainScreenPresenter(
            fetchCurrencyUseCase = RemoteFetchCurrencyUseCase(
                networkService = NetworkModule.networkService
            ),
            schedulerProvider = AndroidSchedulerProvider()
        )
    }

    private val arrayAdapter by lazy {
        ArrayAdapter<CurrencyModel>(this, android.R.layout.simple_list_item_1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_list.adapter = arrayAdapter

        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun displayData(list: List<CurrencyModel>) {
        arrayAdapter.clear()
        arrayAdapter.addAll(list)
    }

    override fun toggleLoadingIndicator(loading: Boolean) {
        activity_main_progress_bar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun displayError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}