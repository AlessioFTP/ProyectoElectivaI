package com.example.proyectoelectivai.datos.remoto

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SteamGridDBApiCliente {
    const val URL_BASE = "https://www.steamgriddb.com/api/v2/"
    const val API_KEY = "45397f4e0914c4f6ef3097a76f9f2613"

    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val nuevaSolicitud = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $API_KEY")
            .build()
        chain.proceed(nuevaSolicitud)
    }
        .build()

    val instancia: SteamGridDBApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SteamGridDBApi::class.java)
    }

}