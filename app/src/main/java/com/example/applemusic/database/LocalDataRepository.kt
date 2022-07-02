package com.example.applemusic.database

import com.example.applemusic.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


interface LocalDataRepository{
    fun getRockMusic(): Single<List<DomainMusic>>
    fun getClassicMusic(): Single<List<DomainMusic>>
    fun getPopMusic(): Single<List<DomainMusic>>
    //fun getAllMusic(): Single<List<DomainMusic>>

    fun insertMusic(music: List<DomainMusic>): Completable
    fun getMusicID(musicID: Int): Single<DomainMusic>
}


class LocalDataRepositoryImpl @Inject constructor(
    private val musicDAO: MusicDAO
): LocalDataRepository {




    override fun getRockMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllMusic()
    }
    override fun getClassicMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllMusic()
    }
    override fun getPopMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllMusic()
        //pass primary key to differate each call instead of getAllmusic(
    }

    override fun insertMusic(music: List<DomainMusic>): Completable {
        return musicDAO.insertAllSongs(*music.toTypedArray())
    }

    override fun getMusicID(musicID: Int): Single<DomainMusic> {
        return musicDAO.getSongById(musicID)
    }

}