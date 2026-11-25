package com.example.exposproj.Models

import com.google.gson.annotations.SerializedName

data class ModelPokemon(@SerializedName("name") val nombre: String, @SerializedName("weight") val peso: Int) {
}