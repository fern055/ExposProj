package com.example.exposproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exposproj.ui.theme.ExposProjTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExposProjTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DispatchTest(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CrTest(modifier: Modifier = Modifier) {
    var progress by remember { mutableFloatStateOf(0f) }
    var message by remember { mutableStateOf("Listo para iniciar descarga") }
    var isDownloading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(message, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth().height(8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("${(progress * 100).toInt()}%")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { coroutineScope.launch {
                    isDownloading = true
                    progress = 0f
                    message = "Descargando paquete..."
                    for (i in 1..100) {
                        delay(40)
                        progress = i / 100f
                    }
                    message = "¡Descarga completada!"
                    isDownloading = false
                } },
            enabled = !isDownloading) {
            Text(if (isDownloading) "Procesando..." else "Iniciar Descarga")
        }
    }
}

@Composable
fun DispatchTest(modifier: Modifier){
    var textState by remember { mutableStateOf("Presiona el boton")}
    var isLoading by remember {mutableStateOf(false)}
    val Scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(textState, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        if(isLoading){
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(24.dp))
        }
        Button(onClick = {Scope.launch { isLoading = true
        textState = "Cambiando a hilo io para descargar..."
        val ioWorkRes = withContext(Dispatchers.IO){
            delay(10000)
            "Datos recuperados con exito desde el servidor"
        }
        textState = ioWorkRes
        isLoading = false}}, enabled = !isLoading){
            Text(if(isLoading){
                "Procesando en IO"
            }
            else{
                "Consultar servidor"
            })
        }
    }
}

@Composable
fun ExtraDispatch(modifier: Modifier){
    var logDefault by remember { mutableStateOf("Default: Sin iniciar") }
    var logUnconfined by remember { mutableStateOf("Unconfined : Sin iniciar") }
    val Scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(logDefault, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(logUnconfined, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            Scope.launch {
                logDefault = "Default Cargando..."
                val calcTime = withContext(Dispatchers.Default){
                    val start = System.currentTimeMillis()
                    var addition = 0L
                    for(i in 1..50000000){
                        addition+= i
                    }
                    val fin = System.currentTimeMillis()
                    "Terminando en ${fin - start} ms | Hilo ${Thread.currentThread().name}"
                }
                logDefault = calcTime
            }
            Scope.launch(Dispatchers.Unconfined) {
                logUnconfined = "Inicio -> Hilo ${Thread.currentThread().name}"
                delay(6767)
                logUnconfined = "Tras delay -> Hilo ${Thread.currentThread().name}"
            }
        }) { Text("Probar dispatchers") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExposProjTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DispatchTest(modifier = Modifier.padding(innerPadding))
        }
    }
}