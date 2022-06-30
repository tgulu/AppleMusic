package com.example.applemusic.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applemusic.model.Music
import com.example.applemusic.model.MusicResponse


@Entity(tableName = "music_database")
data class DomainMusic(

    @PrimaryKey
    val id: Int,
    val songId: Int,
    val artistsName: String,
    val collectionName: String,
    val artWorkUrl60: String,
    val trackPrice: Double
    // val level: String
)

fun List<Music>.mapToDomainSong(): List<DomainMusic> {
    return this.map { networkSong ->

        DomainMusic(
            id = networkSong.artistId ?: 99999,
            songId = networkSong.trackId ?: 99999,
            artistsName = networkSong.artistName ?: "Unknown Artist",
            collectionName = networkSong.collectionName ?: "Unknown Collection",
            artWorkUrl60 = networkSong.artworkUrl60 ?: "Unknown Image",
            trackPrice = networkSong.trackPrice ?: 99999.99
        )
    }
}

