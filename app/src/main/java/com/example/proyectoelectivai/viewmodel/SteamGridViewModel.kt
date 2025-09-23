package com.example.proyectoelectivai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoelectivai.datos.modelo.Grid
import com.example.proyectoelectivai.datos.remoto.SteamGridDBApiCliente
import kotlinx.coroutines.launch
import retrofit2.await


class SteamGridViewModel : ViewModel() {
    var grids: List<Grid> = emptyList()
        private set

    fun busquedaYCargaDeGrids(nombreJuego: String, cargando: (List<Grid>) -> Unit) {
        viewModelScope.launch {
            try {
                val api = SteamGridDBApiCliente.instancia
                val respuestaBusqueda = api.buscarJuego(nombreJuego).await()

                if (respuestaBusqueda.success && respuestaBusqueda.data.isNotEmpty()) {
                    val idJuego = respuestaBusqueda.data.first().id
                    val respuestaGrids = api.obtenerGrids(idJuego).await()

                    if (respuestaGrids.success) {
                        grids = respuestaGrids.data
                        cargando(grids)
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}