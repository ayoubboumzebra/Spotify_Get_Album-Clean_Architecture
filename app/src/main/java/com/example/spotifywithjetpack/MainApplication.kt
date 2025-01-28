package com.example.spotifywithjetpack

import android.app.Application
import com.example.spotifywithjetpack.data.dataSource.remote.AlbumRemoteDatasource
import com.example.spotifywithjetpack.data.repository.album.AlbumRepository
import com.example.spotifywithjetpack.presentation.albums.viewmodel.AlbumViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(modules = appModule)
        }
    }
}

val appModule = module {
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://spotify23.p.rapidapi.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    }

    fun albumDataSource(retrofit: Retrofit): AlbumRemoteDatasource {
        return retrofit.create(AlbumRemoteDatasource::class.java)
    }
    singleOf(::provideRetrofit)
    singleOf(::albumDataSource)
    singleOf(:: AlbumRepository)
    viewModelOf(::AlbumViewModel)
}