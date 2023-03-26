package com.example.facebookapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PostFeedModel(
    @SerializedName("data")
    var dataPost: List<PostDataModel>
): Serializable