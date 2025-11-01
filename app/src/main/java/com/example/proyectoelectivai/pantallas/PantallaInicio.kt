package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.componentes.BloqueRecomendaciones
import com.example.proyectoelectivai.componentes.CategoriaDeJuegos
import com.example.proyectoelectivai.componentes.TarjetaMiGuia
import com.example.proyectoelectivai.datos.modelo.Guia
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.viewmodel.JuegosViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.chunked
import kotlin.collections.forEach


@Composable
fun PantallaInicio(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val viewModelJuegos: JuegosViewModel = viewModel()
    val resultados by viewModelJuegos.juegosFiltrados.collectAsState()

    var tituloJuego by remember { mutableStateOf("") }
    var tieneFocoBusqueda by remember { mutableStateOf(false) }
    var guiasBuscando by remember { mutableStateOf<List<Guia>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BarraBusqueda(
                query = tituloJuego,
                onQueryChange = {
                    tituloJuego = it
                    viewModelJuegos.filtrarJuegos(it)
                },
                onFocusChanged = { enfocado ->
                    tieneFocoBusqueda = enfocado
                }
            )
        }

        if (tieneFocoBusqueda && resultados.isNotEmpty()) {
            item {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                        .padding(horizontal = 30.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(15.dp))
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    items(resultados) { nombre ->
                        Text(
                            text = nombre,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    tituloJuego = nombre
                                    viewModelJuegos.filtrarJuegos("")
                                    tieneFocoBusqueda = false
                                }
                                .padding(vertical = 6.dp, horizontal = 10.dp),
                            color = Color.White
                        )
                        Divider(color = Color.White)
                    }
                }
            }
        }

        item {
            if (viewModelJuegos.juegoExiste(tituloJuego)) {

                LaunchedEffect(tituloJuego) {
                    guiasBuscando = emptyList()
                    isLoading = true
                    obtenerGuiasDelJuego(tituloJuego) { guias ->
                        guiasBuscando = guias
                        isLoading = false
                    }
                }

                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = "Cargando guías...",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }

                    !isLoading && guiasBuscando.isEmpty() -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .background(
                                    color = Color(0xFF161516),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clip(RoundedCornerShape(20.dp))
                        ) {
                            Text(
                                text = "No se encontraron guías para $tituloJuego",
                                color = Color.White,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                            ) {
                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    onClick = {
                                        tituloJuego = ""
                                        guiasBuscando = emptyList()
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF0000)
                                    ),
                                ) {
                                    Text("Volver a las recomendaciones")
                                }
                            }
                        }
                    }

                    else -> {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 20.dp)
                        ) {
                            guiasBuscando.chunked(2).forEach { grupo ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    grupo.forEach { guia ->
                                        TarjetaMiGuia(
                                            guia.descripcion,
                                            guia.imagenPortada,
                                            modifier = Modifier.clickable {
                                                navController.navigate(
                                                    PantallasApp.PantallaDetalleGuia.crearRuta(
                                                        guia.idGuia,
                                                        guia.usuarioCreador
                                                    )
                                                )
                                            })
                                    }

                                    if (grupo.size == 1) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                CategoriaDeJuegos.entries.forEach { categoria ->
                    BloqueRecomendaciones(
                        juegos = categoria.nombres,
                        titulo = categoria.titulo,
                        onJuegoSeleccionado = { juego ->
                            tituloJuego = juego
                            viewModelJuegos.filtrarJuegos(juego)
                            tieneFocoBusqueda = false
                        }
                    )
                }
            }
        }
    }
}


fun obtenerGuiasDelJuego(tituloJuego: String, onResultado: (List<Guia>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("guias")
        .whereEqualTo("tituloJuegoMinuscula", tituloJuego.lowercase())
        .get()
        .addOnSuccessListener { snapshots ->
            val listaGuias = snapshots.documents.mapNotNull { it.toObject(Guia::class.java) }
            onResultado(listaGuias)
        }
        .addOnFailureListener { e ->
            onResultado(emptyList())
        }
}




