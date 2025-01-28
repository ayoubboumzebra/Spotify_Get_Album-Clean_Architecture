package com.example.spotifywithjetpack._core.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spotifywithjetpack.presentation.albums.page.AlbumDetail
import com.example.spotifywithjetpack.presentation.albums.page.AlbumList

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController, modifier = modifier, startDestination = "AlbumList"
    ) {
        composable("AlbumList") {
            AlbumList(navController = navController)
        }
        composable("AlbumDetail") {
            AlbumDetail(navController = navController)
        }

    }
}