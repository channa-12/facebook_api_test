package com.example.facebookapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PostModel:Serializable{
    @SerializedName("feed")
    var feeds: PostFeedModel? = null
    get() = field
    set(value){
        field = value
    }

}