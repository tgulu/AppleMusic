package com.example.applemusic.presenter

import com.example.applemusic.database.LocalDataRepository
import com.example.applemusic.domain.DomainMusic
import com.example.applemusic.domain.mapToDomainSong
import com.example.applemusic.rest.MusicRepository
import com.example.applemusic.view.ClassicFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PopPresenter @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val musicRepository: MusicRepository,
    private val musicRepo : LocalDataRepository
): PopPresenterContract {

    private var popContract : PopViewContract? = null

//    override fun init(viewContract: PopViewContract) {
//        popContract = viewContract
//    }

    override fun init(viewContract: PopViewContract) {
        popContract = viewContract
    }



    override fun registerToNetwork() {
       // TODO("Not yet implemented")
    }

//    override fun getAllSongs() {
//        TODO("Not yet implemented")
//    }

    override fun getAllSongs() {
        popContract?.loadingSongs(true)
        getMusicFromNetwork()
    }

//    override fun getPopMusic() {
//        popContract?.loadingSongs(true)
//        getMusicFromNetwork()
//    }



    private fun getSongFromDb(){
        musicRepo.getPopMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {popContract?.successSongsResponse(it as MutableList<DomainMusic>,true) },
                {popContract?.error(it) }
            )
            .also {compositeDisposable.add(it) }
    }

    private fun getMusicFromNetwork( ){
        musicRepository.getPopMusic()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable {
                musicRepo.insertMusic(it.music.mapToDomainSong())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { getSongFromDb() },
                { error ->
                    popContract?.error(error)
                    getSongFromDb()
                }
            )
            .also { compositeDisposable.add(it)}
    }

    override fun destroyPresenter() {
        popContract = null
        compositeDisposable.dispose()
    }


}

//interface MusicPresenterContract {
//    fun init(viewConstants: MusicViewContract)
//    fun registerToNetwork()
//    fun getAllSongs()
//    fun destroyPresenter()
//
//}

interface PopPresenterContract{
    fun init(viewContract: PopViewContract)
    fun registerToNetwork()
    fun getAllSongs()
    fun destroyPresenter()
}

interface PopViewContract{
    fun loadingSongs(isLoading: Boolean = false)
    fun successSongsResponse(songs: MutableList<DomainMusic>, isOffline: Boolean = false)
    fun error(error: Throwable)
}

//interface MusicViewContract{
//    fun loadingSongs(isLoading: Boolean = false)
//    fun successSongsResponse(songs: List<DomainMusic>, isOffline: Boolean = false)
//    fun error(error: Throwable)
//}

