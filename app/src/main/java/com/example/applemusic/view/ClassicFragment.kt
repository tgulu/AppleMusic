package com.example.applemusic.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemusic.AppleApp
import com.example.applemusic.adapter.MusicAdapter
import com.example.applemusic.databinding.FragmentClassicBinding
import com.example.applemusic.domain.DomainMusic
import com.example.applemusic.presenter.ClassicPresenterContract
import com.example.applemusic.presenter.ClassicViewContract


import javax.inject.Inject

class ClassicFragment : Fragment(), ClassicViewContract {

    @Inject
    lateinit var classicPresenter : ClassicPresenterContract

    @Inject
    lateinit var musicAdapter: MusicAdapter

    private val binding by lazy{
        FragmentClassicBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppleApp.component.inject(this)

    }


    private fun initRecyclerView(){
        binding.classicRecycler.apply {
            this.layoutManager = LinearLayoutManager (requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter

        }
    }

    private fun classicPresent() {
        classicPresenter.init(this)
        classicPresenter.registerToNetwork()
        classicPresenter.getAllSongs()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        classicPresent()
        initRecyclerView()
        return binding.root
    }



    override fun loadingSongs(isLoading: Boolean) {
//        TODO("Not yet implemented")
    }

    override fun successSongsResponse(songs: MutableList<DomainMusic>, isOffline: Boolean) {
        musicAdapter.onSetMusic(songs)
    }

    override fun error(error: Throwable) {
       Toast.makeText(context, "Classic Error -> $error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        classicPresenter.destroyPresenter()
    }
}




