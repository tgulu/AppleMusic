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

import com.example.applemusic.databinding.FragmentRockBinding
import com.example.applemusic.domain.DomainMusic


import com.example.applemusic.presenter.RockPresenterContract
import com.example.applemusic.presenter.RockViewContract
import javax.inject.Inject

class RockFragment : Fragment(), RockViewContract  {

    @Inject
    lateinit var rockPresenter : RockPresenterContract

    @Inject
    lateinit var musicAdapter: MusicAdapter

    private val binding by lazy{
        FragmentRockBinding.inflate(layoutInflater)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppleApp.component.inject(this)


    }
    private fun initRecyclerView(){
        binding.rockRecycler.apply {
            this.layoutManager = LinearLayoutManager (requireContext(),
                LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter

        }
    }

    private fun rockPresent() {
        rockPresenter.init(this)
        rockPresenter.registerToNetwork()
        rockPresenter.getAllSongs()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        rockPresent()
        initRecyclerView()
        return binding.root
    }

    override fun loadingSongs(isLoading: Boolean) {
    }

    override fun successSongsResponse(songs: MutableList<DomainMusic>, isOffline: Boolean) {
        musicAdapter.onSetMusic(songs)
    }

    override fun error(error: Throwable) {
        Toast.makeText(context, "Rock Error  -> $error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        rockPresenter.destroyPresenter()
    }
}




