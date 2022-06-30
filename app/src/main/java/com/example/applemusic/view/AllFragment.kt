//package com.example.applemusic.view
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.example.applemusic.AppleApp
//import com.example.applemusic.adapter.MusicAdapter
//import com.example.applemusic.databinding.FragmentRockBinding
//import com.example.applemusic.domain.DomainMusic
//import com.example.applemusic.presenter.PopPresenterContract
//import com.example.applemusic.presenter.MusicViewContract
//import javax.inject.Inject
//
//class AllFragment : Fragment(), MusicViewContract {
//
//    @Inject
//    lateinit var musicPresenter : PopPresenterContract
//
//    @Inject
//    lateinit var musicAdapter: MusicAdapter
//
//    private val binding by lazy{
//        FragmentRockBinding.inflate(layoutInflater)
//
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        AppleApp.component.inject(this)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun loadingSongs(isLoading: Boolean) {
//        TODO("Not yet implemented")
//    }
//
//    override fun successSongsResponse(songs: List<DomainMusic>, isOffline: Boolean) {
//        TODO("Not yet implemented")
//    }
//
//    override fun error(error: Throwable) {
//        TODO("Not yet implemented")
//    }
//}
//
//
//
//
