package com.example.quoteofthedayapp.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val screens= listOf(
        BottomBarScreen.Home,
        BottomBarScreen.History,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination= navBackStackEntry?.destination
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen->
                Log.d("Bottom bar ", "BottomBar: $screen $currentDestination")
                if (currentDestination != null) {
                    BottomNavigationItem(screen = screen, currentDestination = currentDestination, navController =navController )
                }else{
                    BottomNavigationItem(screen = screen, currentDestination = NavDestination(BottomBarScreen.Home.title), navController =navController )
                }
            }
        }
    }
}


@Composable
fun BottomNavigationItem(
    screen: BottomBarScreen,
    currentDestination:NavDestination,
    navController: NavController
) {
    IconButton(onClick = {
        if(screen.route==BottomBarScreen.Home.route) navController.popBackStack()
        navController.navigate(screen.route as String){
            if(currentDestination.route!=BottomBarScreen.Home.route)navController.popBackStack()
        }
    }) {
        Icon(
            imageVector = screen.icon,
            contentDescription = screen.title,
            tint = if (currentDestination.hierarchy.any { it.route==screen.route }) Color(0xff31304d) else Color.Gray,
        )
    }
}

sealed class BottomBarScreen (
    val route:Any,
    val title:String,
    val icon:ImageVector
){
    data object Home:BottomBarScreen(
        route = "home",
        title = "Home",
        icon= Icons.Outlined.Home
    )
    data object History:BottomBarScreen(
        route = "history",
        title = "history",
        icon= Icons.Outlined.Face
    )
}

@Preview
@Composable
private fun BottomNavPreview() {

        Surface {
            BottomNavigationBar(navController = rememberNavController())
        }

}