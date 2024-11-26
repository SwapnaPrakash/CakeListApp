package com.swapna.cakelistapp.data.model

import com.google.gson.annotations.SerializedName

data class CakeList (
    @SerializedName("title") val title : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("image") val image : String
)