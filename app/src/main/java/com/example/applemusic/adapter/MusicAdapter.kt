package com.example.applemusic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemusic.R
import com.example.applemusic.domain.DomainMusic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class MusicAdapter (


): RecyclerView.Adapter<MusicAdapter.MusicViewHolder> (){
    private val musicList: MutableList<DomainMusic> = mutableListOf()

    fun onSetMusic( musicSetter : MutableList<DomainMusic>){
        musicList.addAll(musicSetter)

        notifyDataSetChanged()

    }
    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }


    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val musical = musicList [position]
        holder.itemView.Artist.text = musical.artistsName
        holder.itemView.Song.text = musical.collectionName
        holder.itemView.Price.text = "$ ${musical.trackPrice.toString()} USD"
        Picasso.get()
            .load(musical.artWorkUrl60)
            .placeholder(R.drawable.ic_baseline_bolt_24)
            .fit()
            .into(holder.itemView.Image)
    }

    override fun getItemCount(): Int = musicList.size





    }
