package com.example.spotifywithjetpack.presentation.albums.viewmodel

import com.example.spotifywithjetpack.domain.entity.AlbumEntity

sealed class AlbumState {
    data object Initial : AlbumState()
    data object Loading : AlbumState()
    data class Success(val albums: List<AlbumEntity>) : AlbumState()
    data class Failure(val message: String) : AlbumState()

}