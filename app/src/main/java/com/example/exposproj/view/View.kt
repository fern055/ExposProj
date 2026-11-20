package com.example.exposproj.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exposproj.viewmodel.CounterViewModel

@Composable
fun CounterView(modifier: Modifier, viewModel: CounterViewModel = viewModel()){
    val counter by viewModel.counter.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().background(Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text("Contador: ${counter.count}")
        Button(onClick = {viewModel.increase()}){
            Text("Agregar")
        }
        Button(onClick = {viewModel.decrease()}) {
            Text("Disminuir")
        }
    }
}