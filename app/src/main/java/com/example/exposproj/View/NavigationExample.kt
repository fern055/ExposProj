package com.example.exposproj.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import kotlinx.serialization.Serializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Serializable
private data object RutaA: NavKey

@Serializable
private data class RutaB(val id: String): NavKey

@Composable
fun NavigationExample(modifier: Modifier = Modifier){
    val backStack = rememberNavBackStack(RutaA)

    NavDisplay(backStack = backStack, onBack = backStack::removeLastOrNull, entryProvider = entryProvider {
        entry<RutaA>(){
            RouteAScreen(modifier = Modifier) {
                Button(onClick = dropUnlessResumed() {
                    backStack.add(RutaB("123"))
                }) {
                    Text("Continuar", fontSize = 48.sp)
                }
            }
        }
        entry<RutaB>(){ key ->
            RouteBScreen(modifier = Modifier) {
                Text("Route id: ${key.id}", fontSize = 48.sp)
                Button(onClick = dropUnlessResumed() {
                    backStack.removeLastOrNull()
                }) {
                    Text("Volver")
                }
            }
        }
    })
}

@Composable
fun RouteAScreen(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        content()
    }
}

@Composable
fun RouteBScreen(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Column(modifier = Modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        content()
    }
}