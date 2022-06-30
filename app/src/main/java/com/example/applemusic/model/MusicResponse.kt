package com.example.applemusic.model


import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("resultCount")
    val resultCount: Int?,
    @SerializedName("results")
    val music: List<Music>
)