package com.example.exposproj.Api

import com.example.exposproj.Models.ModelPokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName: String): Response<ModelPokemon>
}