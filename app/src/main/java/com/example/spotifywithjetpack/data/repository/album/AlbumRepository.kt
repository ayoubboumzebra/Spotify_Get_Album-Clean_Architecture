package com.example.spotifywithjetpack.data.repository.album

import com.example.spotifywithjetpack.data.dataSource.remote.AlbumRemoteDatasource
import com.example.spotifywithjetpack.data.model.album.AlbumModel
import com.example.spotifywithjetpack.data.model.album.toEntity
import com.example.spotifywithjetpack.domain.entity.AlbumEntity
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AlbumRepository(private val albumRemoteDatasource: AlbumRemoteDatasource) {
    suspend fun getAlbums(): List<AlbumEntity> {
        println("------> AlbumRepository 0")

        return try {
            delay(duration = 5.seconds)
            albumRemoteDatasource.getAlbums().toEntity()

        } catch (e: Exception) {
            println("------> AlbumRepository error $e")
            AlbumModel(
                albums = emptyList()
            ).toEntity()
        }
    }
}