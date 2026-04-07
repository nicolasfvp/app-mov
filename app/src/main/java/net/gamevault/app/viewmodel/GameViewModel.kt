package net.gamevault.app.viewmodel

import net.gamevault.app.model.Game
import net.gamevault.app.model.GameUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        // Mock
        _uiState.update { it.copy(games = listOf(
            Game(1, "Elden Ring", "Explore as vastas terras de...", "https://via.placeholder.com/150", 4.9f, 80, true),
            Game(2, "Hades", "fuja do inferno para...", "https://via.placeholder.com/150", 4.8f, 100),
            Game(3, "Cyberpunk 2077", "Na futuristica cidade de NightCity..", "https://via.placeholder.com/150", 4.2f, 40)
        )) }
    }

    fun onSearchQueryChange(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }
    }

    fun toggleFavorite(gameId: Int) {
        _uiState.update { state ->
            val updatedGames = state.games.map {
                if (it.id == gameId) it.copy(isFavorite = !it.isFavorite) else it
            }
            state.copy(games = updatedGames)
        }
    }

    fun updateProgress(gameId: Int, newProgress: Int) {
        _uiState.update { state ->
            val updatedGames = state.games.map {
                if (it.id == gameId) it.copy(progress = newProgress) else it
            }
            state.copy(games = updatedGames)
        }
    }
}