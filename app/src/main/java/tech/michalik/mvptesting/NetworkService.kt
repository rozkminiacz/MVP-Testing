package tech.michalik.mvptesting

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {
    @GET("tickers/?limit=20")
    fun getTickers(): Single<Wrapper>
}

data class Wrapper(
    @SerializedName("data")
    val data: List<CurrencyModel>
)

/* Example response:
{
  "data": [
    {
      "id": "90",
      "symbol": "BTC",
      "name": "Bitcoin",
      "nameid": "bitcoin",
      "rank": 1,
      "price_usd": "9562.50",
      "percent_change_24h": "-1.67",
      "percent_change_1h": "-0.10",
      "percent_change_7d": "-0.66",
      "price_btc": "1.00",
      "market_cap_usd": "174316837542.82",
      "volume24": 31998671341.006404876708984375,
      "volume24a": 29921526982.0413360595703125,
      "csupply": "18229209.00",
      "tsupply": "18229209",
      "msupply": "21000000"
    },
    {
      "id": "80",
      "symbol": "ETH",
      "name": "Ethereum",
      "nameid": "ethereum",
      "rank": 2,
      "price_usd": "259.40",
      "percent_change_24h": "-2.61",
      "percent_change_1h": "-0.12",
      "percent_change_7d": "-0.24",
      "price_btc": "0.027110",
      "market_cap_usd": "28479900713.07",
      "volume24": 17098667901.7057952880859375,
      "volume24a": 15742750885.6570415496826171875,
      "csupply": "109790429.00",
      "tsupply": "109790429",
      "msupply": ""
    }
  ],
  "info": {
    "coins_num": 3777,
    "time": 1582625691
  }
}
 */