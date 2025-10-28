package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyectoelectivai.viewmodel.SteamGridViewModel

@Composable
fun TarjetaMiGuia(
    titulo: String,
    grid: String
) {

    Box(
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        AsyncImage(
            model = grid,
            contentDescription = "Grid Image",
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
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
            AsyncImage(
                model = grid,
                contentDescription = "Grid Image",
                modifier = Modifier
                    .size(160.dp)
                    .padding(end = 20.dp, start = 20.dp, top = 20.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            Text(text = titulo, color = Color.White, maxLines = 2, textAlign = TextAlign.Center)
        }

    }

}