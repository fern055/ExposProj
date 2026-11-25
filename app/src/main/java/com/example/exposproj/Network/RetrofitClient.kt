package com.example.exposproj.Network

import com.example.exposproj.Api.PokeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    val apiService: PokeApiService by lazy {
        retrofit.create(PokeApiService:: class.java)
    }
}