package com.example.applemusic.rest

import com.example.applemusic.model.MusicResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicServiceApi {

    @GET(SONG_PATH)
    fun getSongs(
        @Query("term") musicType: String,
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50
    ): Single<MusicResponse>

    companion object {

        const val BASE_URL = "https://itunes.apple.com/"
        const val SONG_PATH = "search"

    }
}