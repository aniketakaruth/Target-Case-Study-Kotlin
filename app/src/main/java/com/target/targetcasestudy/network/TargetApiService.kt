package com.target.targetcasestudy.network

import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.ProductsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

 interface TargetApiService {

    @GET("deals")
    fun getDeals(): Observable<DealItem>

    @GET("deals/{deal_id}")
    fun getDealDetail(@Path("deal_id") path: Int): Observable<ProductsData>

}
