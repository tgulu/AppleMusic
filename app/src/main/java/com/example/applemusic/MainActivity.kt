package com.example.applemusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.applemusic.adapter.TabAdapter
import com.example.applemusic.view.ClassicFragment
import com.example.applemusic.view.PopFragment
import com.example.applemusic.view.RockFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabs()


    }
    private fun setUpTabs() {
        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(RockFragment())
        adapter.addFragment(ClassicFragment())
        adapter.addFragment(PopFragment())

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        setTabs()
    }

    private fun setTabs(){
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_bolt_24)
        tabs.getTabAt(0)!!.text = "Rock"
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_music_note)
        tabs.getTabAt(1)!!.text = "Classic"
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_headset_foreground)
        tabs.getTabAt(2)!!.text = "Pop"
    }

}

