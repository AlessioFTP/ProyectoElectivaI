package com.example.proyectoelectivai.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoelectivai.componentes.BarraBusqueda
import com.example.proyectoelectivai.componentes.BloqueRecomendaciones
import com.example.proyectoelectivai.componentes.CategoriaDeJuegos
import com.example.proyectoelectivai.navegacion.PantallasApp


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


        // boton temproal de busqueda de minecraft
        Button(
            onClick = {navController.navigate(PantallasApp.PantallaBusquedaGuia.ruta)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(
                text = "Buscar Guías de Minecraft",
                fontSize = 20.dp.value.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

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


