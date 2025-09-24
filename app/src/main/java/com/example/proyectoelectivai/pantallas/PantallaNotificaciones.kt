package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoelectivai.componentes.Notificacion
import com.example.proyectoelectivai.R


@Composable
fun PantallaNotificaciones(navController: NavController, modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 30.dp),
    ) {
        Notificacion(
            R.drawable.imagenmessiejemplo,
            "Messi ha actualizado su guía de eFootball2024",
            "efootball 2024",
        )

        Spacer(modifier = Modifier.height(40.dp))

        Notificacion(
            R.drawable.imagenanuelejemplo,
            "Anuel ha actualizado su guía de UFC3",
            "UFC3",
        )

        Spacer(modifier = Modifier.height(40.dp))

        Notificacion(
            R.drawable.imagenpedriejemplo,
            "Pedri ha actualizado su guía de Hogwarts legacy",
            "hogwarts legacy",
        )

        Spacer(modifier = Modifier.height(40.dp))

        Notificacion(
            R.drawable.imagenyamalejemplo,
            "Lamine ha actualizado su guía de Grounded",
            "Grounded",
        )
    }
}


