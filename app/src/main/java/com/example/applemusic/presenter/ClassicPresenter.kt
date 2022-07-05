package com.example.applemusic.presenter

import android.util.Log
import com.example.applemusic.database.LocalDataRepository
import com.example.applemusic.domain.DomainMusic
import com.example.applemusic.domain.mapToDomainSong
import com.example.applemusic.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClassicPresenter  @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val musicRepository: MusicRepository,
    private val musicRepo : LocalDataRepository
): ClassicPresenterContract {

    private var classicContract : ClassicViewContract? = null

    override fun init(viewContract: ClassicViewContract) {
        classicContract = viewContract
    }

    override fun registerToNetwork() {
    }

    override fun getAllSongs() {
        val TAG = "Network"
        Log.d(TAG, "Network call")
        classicContract?.loadingSongs(true)
        getMusicFromNetwork()
    }



    private fun getSongFromDb(){
        musicRepo.getClassicMusic("classick")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {classicContract?.successSongsResponse(it as MutableList<DomainMusic>,true) },
                {classicContract?.error(it) }
            )
            .also {compositeDisposable.add(it) }
    }

    private fun getMusicFromNetwork( ){

        musicRepository.getClassicMusic()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                musicRepo.insertMusic(it.music.mapToDomainSong("classick"))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getSongFromDb() },
                { error ->
                    classicContract?.error(error)
                    getSongFromDb()
                }
            )
            .also { compositeDisposable.add(it)}
    }

    override fun destroyPresenter() {
        classicContract = null
        compositeDisposable.dispose()
    }


}

interface ClassicPresenterContract{
    fun init(viewContract: ClassicViewContract)
    fun registerToNetwork()
    fun getAllSongs()
    fun destroyPresenter()
}

interface ClassicViewContract{
    fun loadingSongs(isLoading: Boolean = false)
    fun successSongsResponse(songs: MutableList<DomainMusic>, isOffline: Boolean = false)
    fun error(error: Throwable)
}