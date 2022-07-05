package com.example.applemusic.database

import com.example.applemusic.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


interface LocalDataRepository{
    fun getRockMusic(genre: String): Single<List<DomainMusic>>
    fun getClassicMusic(genre: String): Single<List<DomainMusic>>
    fun getPopMusic(genre: String): Single<List<DomainMusic>>
   // fun getMusicByGenre(genre: String): Single<List<DomainMusic>>
    //fun getAllMusic(): Single<List<DomainMusic>>
    fun insertMusic(music: List<DomainMusic>): Completable
    fun getMusicID(musicID: Int): Single<DomainMusic>
}


class LocalDataRepositoryImpl @Inject constructor(
    private val musicDAO: MusicDAO
): LocalDataRepository {



    override fun getRockMusic(genre: String): Single<List<DomainMusic>> {
        return musicDAO.getMusicByGenre("rock")
    }
    override fun getClassicMusic(genre: String): Single<List<DomainMusic>> {
        return musicDAO.getMusicByGenre("classick")
    }

    override fun getPopMusic(genre: String): Single<List<DomainMusic>> {
        return musicDAO.getMusicByGenre("pop")

    }

    override fun insertMusic(music: List<DomainMusic>): Completable {
        return musicDAO.insertAllSongs(*music.toTypedArray())
    }

    override fun getMusicID(musicID: Int): Single<DomainMusic> {
        return musicDAO.getSongById(musicID)
    }

}

