package com.example.applemusic.DI

import com.example.applemusic.presenter.*
import dagger.Binds
import dagger.Module


@Module
abstract class PresenterModule {

    @Binds
    abstract fun popMusicPresenter (

        popPresenter: PopPresenter
        ): PopPresenterContract

    @Binds
    abstract fun rockMusicPresenter(
        rockPresenter: RockPresenter
        ): RockPresenterContract


    @Binds
    abstract fun classicMusicPresenter(
        classicPresenter: ClassicPresenter
        ): ClassicPresenterContract


}

