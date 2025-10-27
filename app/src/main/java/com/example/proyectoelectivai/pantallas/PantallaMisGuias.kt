package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoelectivai.componentes.TarjetaMiGuia


@Composable
fun PantallaMisGuias(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TarjetaMiGuia(titulo = "Guia de balatro", juego = "balatro")
            TarjetaMiGuia(titulo = "Guia para principiantes 7 days", juego = "7 days to die")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TarjetaMiGuia(titulo = "Como subir de rango en futchampions", juego = "fc26")
            TarjetaMiGuia(titulo = "Guia para speedrun", juego = "minecraft")
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TarjetaMiGuia(titulo = "Como farmear la petrolera facil", juego = "rust")
            TarjetaMiGuia(titulo = "Guia de construcción avanzada", juego = "7 days to die")
        }

    }

}