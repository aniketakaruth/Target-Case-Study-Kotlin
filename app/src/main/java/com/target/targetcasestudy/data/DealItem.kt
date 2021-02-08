package com.target.targetcasestudy.data

import com.google.gson.annotations.SerializedName

data class DealItem(
  @SerializedName("products") val productsList: ArrayList<ProductsData>?
)
data class ProductsData(
    @SerializedName("id")  val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("aisle")  val aisle: String,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("regular_price") val regularPriceData: RegularPriceData,
    @SerializedName("sale_price") val salePriceData:SalePriceData

)

data class RegularPriceData(
    @SerializedName("amount_in_cents")  val id: Int,
    @SerializedName("currency_symbol") val currencySymbol: String,
    @SerializedName("display_string")  val priceString: String
)

data class SalePriceData(
    @SerializedName("amount_in_cents")  val id: Int,
    @SerializedName("currency_symbol") val currencySymbol: String,
    @SerializedName("display_string")  val priceString: String
)