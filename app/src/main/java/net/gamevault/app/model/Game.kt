package net.gamevault.app.model

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String = "",
    val rating: Float = 0f,
    val progress: Int = 0,
    val isFavorite: Boolean = false
)