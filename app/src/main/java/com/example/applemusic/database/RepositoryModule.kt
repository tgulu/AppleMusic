package com.example.applemusic.database

import com.example.applemusic.rest.MusicRepository
import com.example.applemusic.rest.MusicRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideLocalRepository(
        localDataRepositoryImpl: LocalDataRepositoryImpl

    ): LocalDataRepository

    @Binds
    abstract fun provideNetworkRepository(
        networkRepositoryImpl: MusicRepositoryImpl
    ): MusicRepository


}