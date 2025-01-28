package com.example.spotifywithjetpack.presentation.albums.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifywithjetpack.data.model.album.AlbumModel
import com.example.spotifywithjetpack.data.repository.album.AlbumRepository
import com.example.spotifywithjetpack.domain.entity.AlbumEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<AlbumState>(AlbumState.Initial)
    val uiState: StateFlow<AlbumState> = _uiState.asStateFlow()

    init {
        println("------> this is AlbumViewModel")
        viewModelScope.launch {
            try {
                println("---> 0")
                _uiState.update {
                    AlbumState.Loading
                }
                val res = albumRepository.getAlbums()
                println("---> 1")
                println("-----> ${res}")
                _uiState.update {
                    AlbumState.Success(albums = res)
                }
            } catch (e: Exception) {
                _uiState.update {
                    AlbumState.Failure(message = "Error loading albums")
                }
            }
        }
    }
}