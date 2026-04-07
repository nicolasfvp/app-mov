package net.gamevault.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onNavigateToLibrary: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.SportsEsports, contentDescription = null, modifier = Modifier.size(100.dp))
        Spacer(Modifier.height(16.dp))
        Text("Bem-vindo ao GameVault", style = MaterialTheme.typography.headlineMedium)
        Card(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Text("Organize sua coleção e acompanhe seu progresso.", Modifier.padding(16.dp))
        }
        Button(onClick = onNavigateToLibrary) {
            Text("Ver Minha Biblioteca")
        }
    }
}