package com.example.facebookapi.screens.facebookDataScreens.common

import com.example.facebookapi.screens.facebookDataScreens.interfaceRetrofit.RetrofitService
import com.example.facebookapi.screens.facebookDataScreens.retrofit.RetrofitClient

object Common {
    //    private var facebook: String = "https://graph.facebook.com/v14.0/"
//    private var field: String = "feed{id,created_time,message,is_published,status_type,full_picture}"
//    private var token: String = "EAAIVJ8BrFzgBAB6Vb5yjbTNzM5gbS6rMFx4gv5LfOwWYm8L2RdHgMWgFgo6hzNaJFo1eyiZAeBZBbznRSr3mwipLRvjAQJufn6l3XpvVeykPmtoHO73aByM9BWgXKojtUPshmhmBzttZBOCOxixjWZB48BaaB5wsoZCoMwVZBYhwZDZD"
//    private var urlGetPost: String = "$facebook/me?fields=$field&token=$token"
//    private val BASE_URL = urlGetPost
    private var facebook: String = "https://graph.facebook.com/v14.0/"
    private val BASE_URL = facebook

    val retrofitService: RetrofitService
        get() = RetrofitClient.getRetrofitClient(BASE_URL).create(RetrofitService::class.java)
}
