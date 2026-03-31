import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamevault.navigation.Screen
import com.example.gamevault.viewmodel.GameViewModel
import com.example.gamevault.ui.DetailScreen
import com.example.gamevault.ui.SummaryScreen

@Composable
fun GameNavHost(navController: NavHostController, viewModel: GameViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
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