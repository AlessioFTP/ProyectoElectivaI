package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoelectivai.R
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.navegacion.PantallasApp


@Composable
fun PantallaNuevaGuia(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.DarkGray),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
        ) {
            var query by remember { mutableStateOf("") }

            BarraBusqueda(query = query, onQueryChange = { query = it })

            Column(
                modifier = Modifier
                    .clickable() {}) {
                Image(
                    painter = painterResource(id = R.drawable.imagen_nueva_guia),
                    contentDescription = "Imagen nueva guia",
                    modifier = Modifier
                        .size(230.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                )

                Text(
                    text = "Agregar Imagen",
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 30.dp.value.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
            }

            var texto by remember { mutableStateOf("") }

            TextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .border(width = 4.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                )
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { /**/ },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .border(
                            BorderStroke(2.dp, Color.White),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icono_boton_aceptar_nueva_guia),
                        contentDescription = "Nueva Guia",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                }
            }


        }
    }
}


