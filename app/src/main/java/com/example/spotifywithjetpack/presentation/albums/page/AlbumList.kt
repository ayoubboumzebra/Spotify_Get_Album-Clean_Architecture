package com.example.spotifywithjetpack.presentation.albums.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.spotifywithjetpack.presentation.albums.viewmodel.AlbumState
import com.example.spotifywithjetpack.presentation.albums.viewmodel.AlbumViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumList(navController: NavController, vm: AlbumViewModel = koinViewModel()) {
    val itemsIndexedList = listOf("A", "B", "C", "E", "F")
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Album List")
                },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->


        when (uiState) {
            is AlbumState.Failure -> Text((uiState as AlbumState.Failure).message)
            AlbumState.Initial -> Box(Modifier)
            AlbumState.Loading -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = Color.White,
                )
            }

            is AlbumState.Success -> LazyVerticalGrid(
                modifier = Modifier.padding(innerPadding),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                content = {
                    items((uiState as AlbumState.Success).albums) { item ->
                        Box(
                            Modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(Color.random())
                                .padding(start = 5.dp, top = 5.dp)
                                .height(100.dp)

                        ) {
                            Text(item.title, color = Color.White)
                            AsyncImage(
                                model = item.cover, contentDescription = "", modifier = Modifier
                                    .width(80.dp)
                                    .offset(x = 10.dp)
                                    .align(Alignment.BottomEnd)
                                    .rotate(degrees = 15F)
                            )

                        }
                    }
                },
            )
        }


    }
}

fun Color.Companion.random(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}