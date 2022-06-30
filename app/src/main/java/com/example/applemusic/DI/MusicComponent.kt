package com.example.applemusic.DI

import com.example.applemusic.MainActivity
import com.example.applemusic.database.NetworkModule
import com.example.applemusic.database.RepositoryModule
import com.example.applemusic.view.ClassicFragment
import com.example.applemusic.view.PopFragment
import com.example.applemusic.view.RockFragment
import dagger.Component



@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        PresenterModule::class
    ]
)


interface MusicComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(rockFragment: RockFragment)
    fun inject(popFragment: PopFragment)
    fun inject(classicFragment: ClassicFragment)
}