package com.example.rxjavaretrofit.data

import com.example.rxjavaretrofit.data.model.Crypto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("prices")
    fun getData(@Query("key") apiKey: String): Observable<List<Crypto>>

}