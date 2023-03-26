package com.example.facebookapi.screens.facebookDataScreens.interfaceRetrofit

import com.example.facebookapi.model.PostDataModel
import com.example.facebookapi.model.PostModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("me?fields=feed%7Bid%2Ccreated_time%2Cmessage%2Cis_published%2Cstatus_type%2Cfull_picture%7D&access_token=EAAIVJ8BrFzgBAB6Vb5yjbTNzM5gbS6rMFx4gv5LfOwWYm8L2RdHgMWgFgo6hzNaJFo1eyiZAeBZBbznRSr3mwipLRvjAQJufn6l3XpvVeykPmtoHO73aByM9BWgXKojtUPshmhmBzttZBOCOxixjWZB48BaaB5wsoZCoMwVZBYhwZDZD")
    fun getFacebookList(): Call<PostModel>

}