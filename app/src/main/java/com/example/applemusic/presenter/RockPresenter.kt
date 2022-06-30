package com.example.applemusic.presenter

import com.example.applemusic.database.LocalDataRepository
import com.example.applemusic.domain.DomainMusic
import com.example.applemusic.domain.mapToDomainSong
import com.example.applemusic.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RockPresenter @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val musicRepository: MusicRepository,
    private val musicRepo : LocalDataRepository
): RockPresenterContract {

    private var rockContract : RockViewContract? = null


    override fun init(viewContract: RockViewContract) {
        rockContract = viewContract
    }

    override fun registerToNetwork() {

    }

    override fun getAllSongs() {
        rockContract?.loadingSongs(true)
        getMusicFromNetwork()
    }



    private fun getSongFromDb(){
        musicRepo.getRockMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {rockContract?.successSongsResponse(it as MutableList<DomainMusic>,true) },
                {rockContract?.error(it) }
            )
            .also {compositeDisposable.add(it) }
    }

    private fun getMusicFromNetwork( ){
        musicRepository.getRockMusic()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                musicRepo.insertMusic(it.music.mapToDomainSong())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getSongFromDb() },
                { error ->
                    rockContract?.error(error)
                    getSongFromDb()
                }
            )
            .also { compositeDisposable.add(it)}
    }

    override fun destroyPresenter() {
        rockContract = null
        compositeDisposable.dispose()
    }


}

interface RockPresenterContract {
    fun init(viewContract: RockViewContract)
    fun registerToNetwork()
    fun getAllSongs()
    fun destroyPresenter()

}

interface RockViewContract{
    fun loadingSongs(isLoading: Boolean = false)
    fun successSongsResponse(songs: MutableList<DomainMusic>, isOffline: Boolean = false)
    fun error(error: Throwable)
}