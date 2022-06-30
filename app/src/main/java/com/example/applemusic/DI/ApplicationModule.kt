package com.example.applemusic.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.applemusic.adapter.MusicAdapter
import com.example.applemusic.database.MusicDAO
import com.example.applemusic.database.MusicDatabase


import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun provideRoomDB(context: Context): MusicDatabase =
        Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            "music-db"
        )
            //.addMigrations(MIGRATION_1_2)
            .build()

    @Provides
    fun providesCardDAO(database: MusicDatabase): MusicDAO =
        database.getMusicDAO()

    @Provides
    fun musicProvider(): MusicAdapter = MusicAdapter()

    }


