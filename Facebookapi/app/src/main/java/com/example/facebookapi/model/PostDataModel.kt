package com.example.facebookapi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PostDataModel(
    @SerializedName("id")
    var postId: String? = null,
    var created_time: String? = null,
    var message: String? = null,
    var is_published: String? = null,
    var status_type: String? = null,
    var full_picture: String? = null
) : Serializable