package com.example.gamevault.model

data class GameUiState(
    val games: List<Game> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
) {
    val filteredGames: List<Game>
        get() = if (searchQuery.isBlank()) games
                else games.filter { it.title.contains(searchQuery, ignoreCase = true) }
}