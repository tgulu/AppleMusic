package com.example.applemusic

import android.app.Application
import com.example.applemusic.DI.ApplicationModule
import com.example.applemusic.DI.DaggerMusicComponent
import com.example.applemusic.DI.MusicComponent

class AppleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerMusicComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object{
        lateinit var component: MusicComponent
    }
}