package com.example.gamevault.model

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val progress: Int,
    val isFavorite: Boolean = false
)