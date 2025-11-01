package com.example.proyectoelectivai.componentes

enum class CategoriaDeJuegos(val nombres: List<String>, val titulo: String) {
    JUEGOS_CASUALES(
        titulo = "Juegos Casuales",
        nombres = listOf(
            "overcooked",
            "stardew valley",
            "slime rancher",
            "terraria",
            "the sims 4",
            "brawhalla"
        )
    ),
    JUEGOS_ACCION(
        titulo = "Juegos Accion",
        nombres = listOf(
            "The last of us 2",
            "Rust",
            "7 days to die",
            "Call of Duty®: Warzone™",
            "left 4 dead 2",
            "payday 2"
        )
    ),
    JUEGOS_COMPETITIVOS(
        titulo = "Juegos Competitivos",
        nombres = listOf(
            "valorant",
            "league of legends",
            "csgo",
            "dota 2",
            "rocket league",
            "apex legends"
        )
    ),
    JUEGOS_DEPORTIVOS(
        titulo = "Juegos Deportivos",
        nombres = listOf("FC 26", "NBA 2K24", "Madden NFL 24", "WWE 2K24", "NHL 24")
    ),
}