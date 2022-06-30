package com.example.applemusic.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applemusic.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface MusicDAO {

    @Query("SELECT * FROM music_database")
    fun getAllMusic(): Single<List<DomainMusic>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertAllSongs(vararg songs:DomainMusic): Completable

    @Query("SELECT * FROM music_database WHERE id LIKE :artistId")
    fun getSongById(artistId:Int):Single <DomainMusic>


}