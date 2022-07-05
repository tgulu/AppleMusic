package com.example.applemusic.rest

import com.example.applemusic.model.MusicResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicServiceApi {


    @GET(ROCK_PATH)
    fun getAllRockSongs(
        @Query("term") musicType: String,
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50
    ): Single<MusicResponse>

    @GET(CLASSIC_PATH)
    fun getAllClassicSongs(
        @Query("term") musicType: String,
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50
    ): Single<MusicResponse>

    @GET(POP_PATH)
    fun getAllPopSongs(
        @Query("term") musicType: String,
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50
    ): Single<MusicResponse>

    companion object {

        const val BASE_URL = "https://itunes.apple.com/"
        //private const val SONG_PATH = "search"
        private const val ROCK_PATH = "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
        private const val CLASSIC_PATH = "search?term=classick&amp;media=music&amp;entity=song&amp;limit=50"
        private const val POP_PATH = "search?term=pop&amp;media=music&amp;entity=song&amp;limit=50"

    }
}