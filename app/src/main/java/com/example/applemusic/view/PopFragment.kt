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
import com.example.applemusic.databinding.FragmentPopBinding
import com.example.applemusic.domain.DomainMusic
import com.example.applemusic.presenter.PopPresenterContract

import com.example.applemusic.presenter.PopViewContract
import javax.inject.Inject

class PopFragment : Fragment(), PopViewContract {

    @Inject
    lateinit var popPresenter : PopPresenterContract

    @Inject
    lateinit var musicAdapter: MusicAdapter

    private val binding by lazy{
        FragmentPopBinding.inflate(layoutInflater)

    }

    private fun initRecyclerView(){
        binding.popRecycler.apply {
            this.layoutManager = LinearLayoutManager (requireContext(),
                LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter

        }
    }

    private fun popPresent() {
        popPresenter.init(this)
        popPresenter.registerToNetwork()
        popPresenter.getAllSongs()



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppleApp.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        popPresent()
        initRecyclerView()
        return binding.root
    }



    override fun loadingSongs(isLoading: Boolean) {
        //TODO("Not yet implemented")
    }

    override fun successSongsResponse(songs: MutableList<DomainMusic>, isOffline: Boolean) {
        musicAdapter.onSetMusic(songs)
    }


    override fun error(error: Throwable) {
        Toast.makeText(context, "Pop Error -> $error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        popPresenter.destroyPresenter()
    }
}




