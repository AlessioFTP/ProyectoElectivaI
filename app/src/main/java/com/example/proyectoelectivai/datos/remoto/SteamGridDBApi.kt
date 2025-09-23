package com.example.proyectoelectivai.datos.remoto

import com.example.proyectoelectivai.datos.modelo.RespuestaBusqueda
import com.example.proyectoelectivai.datos.modelo.RespuestaGrid
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface SteamGridDBApi {
    @GET("search/autocomplete/{nombre}")
    fun buscarJuego(@Path("nombre") nombre: String): Call<RespuestaBusqueda>

    @GET("grids/game/{id}")
    fun obtenerGrids(@Path("id") id: Int): Call<RespuestaGrid>
}