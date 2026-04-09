package net.gamevault.app.model

data class Achievement(
    val id: Int,
    val name: String,
    val completed: Boolean = false
)

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String = "",
    val rating: Int = 0,
    val progress: Int = 0,
    val isFavorite: Boolean = false,
    val achievements: List<Achievement> = emptyList()
)