package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectoelectivai.componentes.ApartadoEditor
import com.example.proyectoelectivai.componentes.ApartadosDetalleGuia
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.componentes.BloqueRecomendaciones
import com.example.proyectoelectivai.componentes.CategoriaDeJuegos
import com.example.proyectoelectivai.componentes.ImagenSuperiorDetalleGuia
import com.example.proyectoelectivai.datos.modelo.Apartado
import com.example.proyectoelectivai.datos.modelo.Bloque
import com.example.proyectoelectivai.datos.modelo.Guia
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun PantallaEditarGuia(
    navController: NavController,
    modifier: Modifier,
    idGuia: String
) {
    var guia by remember { mutableStateOf<Guia?>(null) }

    LaunchedEffect(idGuia) {
        guia = buscarGuiaPorId(idGuia)
    }

    guia?.let { datosGuia ->

        var apartados by remember { mutableStateOf(datosGuia.apartados.toMutableList()) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color(0xFF0A0F11))
                .verticalScroll(rememberScrollState()),
        ) {

            Text(
                text = "Editar Guía: ${datosGuia.descripcion}",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp, bottom = 0.dp, start = 20.dp, end = 20.dp)
            )

            apartados.forEachIndexed { index, apartado ->
                ApartadoEditor(
                    apartado = apartado,
                    onChange = { nuevo ->
                        apartados = apartados.toMutableList().also { it[index] = nuevo }
                    },
                    onDelete = {
                        apartados = apartados.toMutableList().also { it.removeAt(index) }
                    }
                )
                Spacer(Modifier.height(20.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        apartados = apartados.toMutableList().apply {
                            add(
                                Apartado(
                                    titulo = "Nuevo apartado",
                                    contenido = listOf(
                                        Bloque(tipo = "texto", titulo = "", valor = "")
                                    )
                                )
                            )
                        }
                    },
                ) {
                    Text("Agregar apartado")
                }

                Button(
                    onClick = {
                        val guiaActualizada = datosGuia.copy(apartados = apartados)
                        actualizarGuiaEnFirestore(guiaActualizada)
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA42500)),
                ) {
                    Text("Guardar cambios", color = Color.White)
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                onClick = {
                    borrarGuiaEnFirestore(datosGuia)
                    navController.navigate(PantallasApp.PantallaMisGuias.ruta)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF0000)),
            ) {
                Text("Eliminar guía")
            }

        }

    } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Cargando guía...", color = Color.Gray)
    }
}

fun actualizarGuiaEnFirestore(guia: Guia) {
    val db = FirebaseFirestore.getInstance()
    db.collection("guias").document(guia.idGuia).set(guia)
}

fun borrarGuiaEnFirestore(guia: Guia) {
    val db = FirebaseFirestore.getInstance()
    db.collection("guias").document(guia.idGuia).delete()
}



