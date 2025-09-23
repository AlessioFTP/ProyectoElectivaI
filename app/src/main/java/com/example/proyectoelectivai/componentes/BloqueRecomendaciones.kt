package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyectoelectivai.datos.modelo.Grid
import com.example.proyectoelectivai.viewmodel.SteamGridViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp


@Composable
fun BloqueRecomendaciones(
    viewModel: SteamGridViewModel = viewModel(),
    titulo: String,
    juegos: List<String>
) {

    var gridPorJuego by remember { mutableStateOf<Map<String, List<Grid>>>(emptyMap()) }

    LaunchedEffect(juegos) {
        juegos.forEach { juego ->
            viewModel.busquedaYCargaDeGrids(juego) { result ->
                gridPorJuego = gridPorJuego + (juego to result)
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = titulo,
            modifier = Modifier
                .height(25.dp),
            color = Color.White,
            fontSize = 25.dp.value.sp,
            fontWeight = Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
        ) {
            juegos.forEach { juego ->
                val grid = gridPorJuego[juego]?.firstOrNull()
                if (grid != null) {
                    item {
                        Card(
                            modifier = Modifier
                                .width(180.dp)
                                .fillMaxHeight()
                                .border(2.dp, color = Color.Gray, RoundedCornerShape(15.dp))
                                .clip(RoundedCornerShape(15.dp)),
                            elevation = CardDefaults.cardElevation(0.dp),
                            colors = CardDefaults.cardColors(
                                containerColor =
                                    Color.Transparent
                            ),
                        ) {
                            AsyncImage(
                                model = grid.thumb,
                                contentDescription = "Grid Image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent)
                            )
                        }
                    }
                }
            }
        }
    }
}
