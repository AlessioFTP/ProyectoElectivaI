package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.componentes.BloqueRecomendaciones
import com.example.proyectoelectivai.componentes.CategoriaDeJuegos


@Composable
fun PantallaInicio(
    navController: NavController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BarraBusqueda(query = query, onQueryChange = { query = it })

        CategoriaDeJuegos.entries.forEach { categoria ->

            BloqueRecomendaciones(juegos = categoria.nombres, titulo = categoria.titulo)

        }


    }


}

@Preview(showBackground = true)
@Composable
fun PantallaInicioPreview() {
    PantallaInicio()
}


