package com.example.tugasretrofit.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("data")
    val data: List<Vendors>
)
