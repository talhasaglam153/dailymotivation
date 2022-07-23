package com.tcoding.dailymotivation.network

import com.tcoding.dailymotivation.model.Motivation
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {


    @GET("advice")
    fun getAdvice(): Call<Motivation>


}