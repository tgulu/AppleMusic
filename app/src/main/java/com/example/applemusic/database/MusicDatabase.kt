package com.example.applemusic.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.applemusic.domain.DomainMusic


@Database(
    entities = [DomainMusic::class],
    version = 1, exportSchema = false


)

//@TypeConverters(
//    CardPricesConverter::class,
//    CardsImagesConverter::class
//)


abstract class MusicDatabase : RoomDatabase() {
    abstract fun getMusicDAO(): MusicDAO
}

