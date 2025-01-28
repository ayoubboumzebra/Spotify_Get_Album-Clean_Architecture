package com.example.spotifywithjetpack.data.model.album

import com.example.spotifywithjetpack.domain.entity.AlbumEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement


@Serializable
data class AlbumModel (
    val albums: List<Album>
)
fun AlbumModel.toEntity(): List<AlbumEntity> = this.albums.map {
    AlbumEntity(
        title = it.name,
        cover = it.images.first().url,
        releaseDate = it.releaseDate
    )
}
@Serializable
data class Album (
    @SerialName("album_type")
    val albumType: String,

    @SerialName("total_tracks")
    val totalTracks: Long,

    @SerialName("is_playable")
    val isPlayable: Boolean,

    @SerialName("external_urls")
    val externalUrls: ExternalUrls,

    val id: String,
    val images: List<Image>,
    val name: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("release_date_precision")
    val releaseDatePrecision: String,

    val type: String,
    val uri: String,
    val artists: List<Artist>,
    val tracks: Tracks,
    val copyrights: List<Copyright>,

    @SerialName("external_ids")
    val externalIDS: ExternalIDS,

    val genres: JsonArray,
    val label: String,
    val popularity: Long
)

@Serializable
data class Artist (
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,

    val id: String,
    val name: String,
    val type: ArtistType,
    val uri: String
)

@Serializable
data class ExternalUrls (
    val spotify: String
)

@Serializable
enum class ArtistType(val value: String) {
    @SerialName("artist") Artist("artist");
}

@Serializable
data class Copyright (
    val text: String,
    val type: String
)

@Serializable
data class ExternalIDS (
    val upc: String
)

@Serializable
data class Image (
    val url: String,
    val height: Long,
    val width: Long
)

@Serializable
data class Tracks (
    val limit: Long,
    val next: JsonElement? = null,
    val offset: Long,
    val previous: JsonElement? = null,
    val total: Long,
    val items: List<Item>
)

@Serializable
data class Item (
    val artists: List<Artist>,

    @SerialName("disc_number")
    val discNumber: Long,

    @SerialName("duration_ms")
    val durationMS: Long,

    val explicit: Boolean,

    @SerialName("external_urls")
    val externalUrls: ExternalUrls,

    val id: String,

    @SerialName("is_playable")
    val isPlayable: Boolean,

    val name: String,

    @SerialName("preview_url")
    val previewURL: String,

    @SerialName("track_number")
    val trackNumber: Long,

    val type: ItemType,
    val uri: String,

    @SerialName("is_local")
    val isLocal: Boolean
)

@Serializable
enum class ItemType(val value: String) {
    @SerialName("track") Track("track");
}