package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectoelectivai.componentes.TarjetaMiGuia
import com.example.proyectoelectivai.navegacion.PantallasApp
import com.example.proyectoelectivai.viewmodel.AutenticarViewModel
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.lazy.items


@Composable
fun PantallaMisGuias(navController: NavController, modifier: Modifier = Modifier) {
    val viewModelUsuario: AutenticarViewModel = viewModel()

    if (!viewModelUsuario.usuarioLogueado()) {
        navController.navigate(PantallasApp.PantallaInicio.ruta){
            popUpTo(PantallasApp.PantallaMisGuias.ruta) { inclusive = true }
        }
        return
    }

    val uid = viewModelUsuario.obtenerUidActual()!!
    var datosUsuario by remember { mutableStateOf<Map<String, Any>?>(null) }
    LaunchedEffect(uid) {
        datosUsuario = obtenerDatosUsuario(uid)
    }

    val usuarioCreador = datosUsuario?.get("usuario") as? String

    var guiasUsuario by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    LaunchedEffect(usuarioCreador) {
        if (usuarioCreador != null) {
            obtenerGuiasDelUsuario(usuarioCreador) { guias ->
                guiasUsuario = guias
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        items(guiasUsuario.chunked(2)) { grupo ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                grupo.forEach { guia ->
                    TarjetaMiGuia(
                        guia["descripcion"] as String,
                        guia["imagenPortada"] as String,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                PantallasApp.PantallaDetalleGuia.crearRuta(guia["idGuia"] as String, guia["usuarioCreador"] as String)
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

fun obtenerGuiasDelUsuario(usuarioCreador: String, onResutlado: (List<Map<String, Any>>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("guias")
        .whereEqualTo("usuarioCreador", usuarioCreador)
        .get()
        .addOnSuccessListener { snapshots ->
            val listaGuias = snapshots.documents.mapNotNull { it.data }
            onResutlado(listaGuias)
        }
        .addOnFailureListener { e ->
            onResutlado(emptyList())
        }

}

