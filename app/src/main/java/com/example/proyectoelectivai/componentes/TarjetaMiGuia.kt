package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.unit.sp

@Composable
fun TarjetaMiGuia(
    titulo: String,
    imagenUrl: String? = null,
    imagenRes: Int? = null,
    onClick: () -> Unit,
    juego: String = ""
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable { onClick() }
    ) {

        when {
            !imagenUrl.isNullOrBlank() -> {
                AsyncImage(
                    model = imagenUrl,
                    contentDescription = "Imagen fondo $titulo",
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            imagenRes != null -> {
                Image(
                    painter = painterResource(id = imagenRes),
                    contentDescription = "Imagen fondo $titulo",
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.DarkGray)
                        .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp))
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when {
                !imagenUrl.isNullOrBlank() -> {
                    AsyncImage(
                        model = imagenUrl,
                        contentDescription = "Imagen miniatura $titulo",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                imagenRes != null -> {
                    Image(
                        painter = painterResource(id = imagenRes),
                        contentDescription = "Imagen miniatura $titulo",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = titulo,
                color = Color.White,
                maxLines = 2,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}