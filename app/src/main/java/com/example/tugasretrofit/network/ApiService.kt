package com.example.tugasretrofit.network

import com.example.tugasretrofit.model.Result
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("tenders")
    fun getAllResult() : Call<Result>
}