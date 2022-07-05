package com.example.applemusic.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applemusic.model.Music



@Entity(tableName = "music_database")
data class DomainMusic(

    @PrimaryKey
    val id: Int,
    val songId: Int,
    val artistsName: String,
    val collectionName: String,
    val artWorkUrl60: String,
    val trackPrice: Double,
    val genre: String

)

fun List<Music>.mapToDomainSong(genre: String): List<DomainMusic> {
    return this.map { networkSong ->

        DomainMusic(
            genre = genre,
            id = networkSong.artistId ?: 99999,
            songId = networkSong.trackId ?: 99999,
            artistsName = networkSong.artistName ?: "Unknown Artist",
            collectionName = networkSong.collectionName ?: "Unknown Collection",
            artWorkUrl60 = networkSong.artworkUrl60 ?: "Unknown Image",
            trackPrice = networkSong.trackPrice ?: 99999.99
        )
    }
}

