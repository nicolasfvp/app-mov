package net.gamevault.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val GamingColorScheme = darkColorScheme(
    primary = GamingPrimary,
    onPrimary = GamingOnPrimary,
    secondary = GamingSecondary,
    onSecondary = GamingOnSecondary,
    background = GamingBackground,
    onBackground = GamingOnBackground,
    surface = GamingSurface,
    onSurface = GamingOnSurface
)

@Composable
fun GameVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = GamingColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}