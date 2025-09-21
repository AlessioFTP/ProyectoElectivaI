package com.example.proyectoelectivai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectoelectivai.componentes.BarraNavegacion
import com.example.proyectoelectivai.ui.theme.ProyectoElectivaITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoElectivaITheme {
                BarraNavegacion()
            }
        }
    }
}

