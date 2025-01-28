package com.example.spotifywithjetpack.data.dataSource.remote

import com.example.spotifywithjetpack.data.model.album.AlbumModel
import retrofit2.http.GET
import retrofit2.http.Headers


interface AlbumRemoteDatasource {
    @Headers("X-Rapidapi-Key: ")
    @GET("albums/?ids=4Zoxsc06EUHRf5GrJPJZ54,2k8JXNLzRk04iNgEvxunK0,5TqSJDbSP3wZzEJyTyAs9i,5IcsHifVLGG0emBPlMZacj,2LsRvrM6gsxxpg1mN9Z2uA,0fDtkURAIQeQ7Kz7ZC60xi,1kTzTL0iOIE2IAGw83yY4O,56hrhzHAOsm5C7kSXzF4kh,5AgsHUKFxr5DApRCmulIqJ,7eQGtkzCgrIWDOe76E9F8t")
    suspend fun getAlbums() : AlbumModel
}
