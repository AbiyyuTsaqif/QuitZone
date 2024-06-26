package com.example.quitzone.ui.mainfeature

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.quitzone.R
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.ui.theme.ungulagi
import com.example.quitzone.ui.theme.ungulagilagi
import com.example.quitzone.viewmodel.mainfeatureViewModel.PredictionViewModel
import com.example.quitzone.viewmodel.mainfeatureViewModel.PredictionViewModelFactory
import com.example.quitzone.viewmodel.mainfeatureViewModel.WalletViewModel
import com.example.quitzone.viewmodel.mainfeatureViewModel.WalletViewModelFactory

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)
    val viewModelFactory = PredictionViewModelFactory(sharedpreferences)
    val predictionViewModel: PredictionViewModel = viewModel(
        factory = viewModelFactory
    )
    val walletViewModel : WalletViewModel = viewModel(
        factory = WalletViewModelFactory(sharedpreferences)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.padding(0.dp)
    ) {
        NavigationBarItem(
            selected = currentDestination == "home",
            onClick = { navController.navigate("home") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
                      walletViewModel.getWallet(sharedpreferences.getUserToken().toString())
                      },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Home",
                    modifier = Modifier.size(25.dp)
                )
            },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = ungulagi.copy(alpha = 0.5f), // Highlight color for selected item
                selectedTextColor = Color.Unspecified,
                unselectedTextColor = Color.Unspecified,
                selectedIconColor = Color.Unspecified,
                unselectedIconColor = Color.Unspecified
            )
        )
        NavigationBarItem(
            selected = currentDestination == "community",
            onClick = {
                navController.navigate("community") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
           predictionViewModel.getPrediction(sharedpreferences.getUserToken().toString())
                println("community name: ${sharedpreferences.getPredictionValue()}")
//                println("community name : ${sharedpreferences.getPredictionValue()}")
                      },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_community),
                    contentDescription = "Community",
                    modifier = Modifier.size(30.dp)
                )
            },
            label = { Text("Community") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = ungulagi.copy(alpha = 0.5f), // Highlight color for selected item
                selectedTextColor = Color.Unspecified,
                unselectedTextColor = Color.Unspecified,
                selectedIconColor = Color.Unspecified,
                unselectedIconColor = Color.Unspecified
            )
        )
        NavigationBarItem(
            selected = currentDestination == "diary",
            onClick = { navController.navigate("diary") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_diary),
                    contentDescription = "Diary",
                    modifier = Modifier.size(25.dp)
                )
            },
            label = { Text("Diary") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = ungulagi.copy(alpha = 0.5f), // Highlight color for selected item
                selectedTextColor = Color.Unspecified,
                unselectedTextColor = Color.Unspecified,
                selectedIconColor = Color.Unspecified,
                unselectedIconColor = Color.Unspecified
            )
        )
    }
}
