package net.gamevault.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.gamevault.app.viewmodel.GameViewModel
import net.gamevault.app.ui.DetailScreen
import net.gamevault.app.ui.HomeScreen
import net.gamevault.app.ui.LibraryScreen
import net.gamevault.app.ui.SummaryScreen

@Composable
fun GameNavHost(navController: NavHostController, viewModel: GameViewModel, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onNavigateToLibrary = { navController.navigate(Screen.Library.route) })
        }
        composable(Screen.Library.route) {
            LibraryScreen(
                viewModel = viewModel,
                onGameClick = { id -> navController.navigate(Screen.Details.createRoute(id)) },
                onNavigateToSummary = { navController.navigate(Screen.Summary.route) }
            )
        }
        composable(Screen.Details.route) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId")?.toInt()
            DetailScreen(
                gameId = gameId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Summary.route) {
            SummaryScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
        }
    }
}