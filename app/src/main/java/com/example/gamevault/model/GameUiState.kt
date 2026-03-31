package com.example.gamevault.model

data class GameUiState(
    val games: List<Game> = emptyList(), // O erro 'games' ocorre se este nome estiver diferente
    val isLoading: Boolean = false
)