package com.example.applemusic.rest

import com.example.applemusic.model.MusicResponse
import com.example.applemusic.utils.convertDate
import io.reactivex.Single
import javax.inject.Inject

interface MusicRepository {
    fun getRockMusic(): Single<MusicResponse>
//    fun getAllMusic(): Single<MusicResponse>
    fun getClassicMusic(): Single<MusicResponse>
    fun getPopMusic(): Single<MusicResponse>
}

class MusicRepositoryImpl @Inject constructor(
    private val serviceApi: MusicServiceApi
) : MusicRepository {

//    override fun getRockMusic(): Single<MusicResponse> {
//        "mydate".convertDate()
//        return serviceApi.getSongs("rock")
//
//    }

    override fun getRockMusic(): Single<MusicResponse> {
        return serviceApi.getSongs("rock")

    }

    override fun getClassicMusic(): Single<MusicResponse> {
        return serviceApi.getSongs("classic")
    }

    override fun getPopMusic(): Single<MusicResponse> {
        return serviceApi.getSongs("pop")
    }
}