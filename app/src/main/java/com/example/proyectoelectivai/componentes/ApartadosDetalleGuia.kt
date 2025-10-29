package com.example.proyectoelectivai.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyectoelectivai.datos.modelo.Apartado
import com.example.proyectoelectivai.datos.modelo.Guia

@Composable
fun ApartadosDetalleGuia(datosGuia: Guia) {
    val apartados: List<Apartado> = datosGuia.apartados
    var seleccionado by remember {
        mutableStateOf(
            apartados.firstOrNull()?.titulo
        )
    }
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color(0xFF18232B))
                .padding(horizontal = 8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            apartados.forEach { apartado ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            if (seleccionado == apartado.titulo) Color(0xFFA42500) else Color(
                                0x00000000
                            )
                        )
                        .clickable { seleccionado = apartado.titulo }
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = apartado.titulo,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val apartadoSeleccionado = apartados.find { it.titulo == seleccionado }

        apartadoSeleccionado?.let { apartado ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                apartado.contenido.forEach { bloque ->
                    when (bloque.tipo) {
                        "texto" -> {
                            if (bloque.titulo.isNotBlank())
                                Text(
                                    text = bloque.titulo,
                                    fontSize = 30.dp.value.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            Text(
                                text = bloque.valor,
                                fontSize = 15.dp.value.sp,
                                color = Color.White
                            )
                        }

                        "imagen" -> AsyncImage(
                            model = bloque.valor,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}