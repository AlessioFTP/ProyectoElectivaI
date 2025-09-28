package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TarjetaMiGuia(
    titulo: String,
    imagenRes: Int,   // Imagen específica de la guía
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick() } // Maneja el click
    ) {
        // Imagen de fondo
        AsyncImage(
            model = imagenRes,
            contentDescription = "Imagen de $titulo",
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

        // Capa oscura para resaltar el texto
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )

        // Título centrado sobre la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titulo,
                color = Color.White,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
        }
    }
}
