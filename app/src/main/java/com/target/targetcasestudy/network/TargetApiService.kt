package com.target.targetcasestudy.network

import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.ProductsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

 interface TargetApiService {

    @GET("deals")
    fun getDeals(): Observable<DealItem>

    @GET("deals/{product_id}")
    fun getProductDetails(@Path("product_id") path: Int): Observable<ProductsData>

}
