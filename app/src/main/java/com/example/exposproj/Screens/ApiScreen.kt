package com.example.exposproj.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exposproj.Network.RetrofitClient

@Composable
fun ApiScreen(){
    var resultado by remember { mutableStateOf("Cargando un Pokemon") }

    LaunchedEffect(Unit) {
        try{
            val response = RetrofitClient.apiService.getPokemon("Mew")

            if(response.isSuccessful){
                val pokemon = response.body()
                resultado = "Pokemon Encontrado y su nombre es ${pokemon?.nombre} Peso: ${pokemon?.peso}"
            }
            else{
                resultado = "Hubo un error de Codigo: ${response.code()}"
            }
        }
        catch(e: Exception){
            resultado = "Error de conexion: ${e.localizedMessage}"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("La prueba definitiva para encontrar al pokemon que tu buscas, wow que chido!")
        Spacer(modifier = Modifier.height(16.dp))
        Text(resultado)
    }
}

/*
@Composable
@Preview(showBackground = true)
fun Preview(){
    ApiScreen()
}
 */