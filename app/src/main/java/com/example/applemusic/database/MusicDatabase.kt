package com.example.applemusic.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.applemusic.domain.DomainMusic


@Database(
    entities = [DomainMusic::class],
    version = 1, exportSchema = false


)



abstract class MusicDatabase : RoomDatabase() {
    abstract fun getMusicDAO(): MusicDAO
}

