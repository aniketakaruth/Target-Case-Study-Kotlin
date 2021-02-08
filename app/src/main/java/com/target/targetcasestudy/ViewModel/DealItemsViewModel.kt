package com.target.targetcasestudy.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.ProductsData
import com.target.targetcasestudy.network.RetrofitInstance
import com.target.targetcasestudy.network.TargetApiService

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class DealItemsViewModel : ViewModel() {


    var dealsList: MutableLiveData<DealItem> = MutableLiveData()


    var dealsItem: MutableLiveData<ProductsData> = MutableLiveData()

    fun getDealListObserver(): MutableLiveData<DealItem> {
        return dealsList
    }

    fun getDealsItemObserver(): MutableLiveData<ProductsData> {
        return dealsItem
    }

    fun fetchDealsList() {
        val retroInstance = RetrofitInstance.getRetroInstance().create(TargetApiService::class.java)
        retroInstance.getDeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDealListObserverRx())
    }

    private fun getDealListObserverRx(): Observer<DealItem> {
        return object : Observer<DealItem> {
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                dealsList.postValue(null)
            }

            override fun onNext(t: DealItem) {
                dealsList.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
            }
        }
    }

    fun fetchDealDetails(id: Int) {
        val retroInstance = RetrofitInstance.getRetroInstance().create(TargetApiService::class.java)
        retroInstance.getDealDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDealItemObserverRx())
    }

    private fun getDealItemObserverRx(): Observer<ProductsData> {
        return object : Observer<ProductsData> {
            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
                println(e.message)
                dealsItem.postValue(null)
            }

            override fun onNext(t: ProductsData) {
                dealsItem.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
            }
        }
    }


}