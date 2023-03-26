package com.example.facebookapi.screens.facebookDataScreens.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.facebookapi.FacebookAPI
import com.example.facebookapi.R
import com.example.facebookapi.model.PostDataModel
import com.squareup.picasso.Picasso

class FacebookPostDetailAdapter(private val context: Context, arrayList: List<PostDataModel>?, onClickAdapter: OnClickAdapter) :
    RecyclerView.Adapter<FacebookPostDetailAdapter.ViewHolder>() {
    private var listData: List<PostDataModel>? = arrayList
    private var onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtPostId: TextView = view.findViewById<View>(R.id.txt_post_id) as TextView
        var txtMessage: TextView = view.findViewById<View>(R.id.txt_message) as TextView
        var txtStatusType: TextView = view.findViewById<View>(R.id.txt_status_type) as TextView
        var imageFullPicture: ImageView = view.findViewById<View>(R.id.img_full_picture) as ImageView
        var txtIsPublished: TextView = view.findViewById<View>(R.id.txt_is_published) as TextView
        var txtCreatedTime: TextView = view.findViewById<View>(R.id.txt_created_time) as TextView
        var btnAddIntoFirebase: ImageView = view.findViewById(R.id.btn_create_in_firebase) as ImageView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_facebook_post, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val facebookPostDetailList: PostDataModel = listData!![position]
        holder.txtPostId.text = facebookPostDetailList.postId
        holder.txtMessage.text = facebookPostDetailList.message
        holder.txtStatusType.text = facebookPostDetailList.status_type
        Picasso.get().load(facebookPostDetailList.full_picture).into(holder.imageFullPicture)
        holder.txtIsPublished.text = facebookPostDetailList.is_published
        holder.txtCreatedTime.text = facebookPostDetailList.created_time
        holder.btnAddIntoFirebase.setOnClickListener {
            onClickAdapter.onClickMe(facebookPostDetailList)
        }
    }

    override fun getItemCount(): Int {
        return listData!!.size
    }

    interface OnClickAdapter{
        fun onClickMe(postDataModel: PostDataModel)
    }

    init {
        this.onClickAdapter = onClickAdapter
    }
}

/*
curl -i -X GET \
 "https://graph.facebook.com/v14.0/me?fields=feed%7Bid%2Ccreated_time%2Cmessage%2Cis_published%2Cstatus_type%2Cfull_picture%7D&access_token=EAAIVJ8BrFzgBAB6Vb5yjbTNzM5gbS6rMFx4gv5LfOwWYm8L2RdHgMWgFgo6hzNaJFo1eyiZAeBZBbznRSr3mwipLRvjAQJufn6l3XpvVeykPmtoHO73aByM9BWgXKojtUPshmhmBzttZBOCOxixjWZB48BaaB5wsoZCoMwVZBYhwZDZD"
*/

/*
Access token: EAAIVJ8BrFzgBAPildUTXWMhu9flKM8SpwTHSZCwaGfHvJOIkJSMBZBuHcyUwz278Fn3m7MAmx3pjDpFU9n6TX994q9Jw24rLIAyDoyIxV4rtdxbkpthJ1n5sNmlsp8ztCkuZC27JUIRYYRavTaVuqf2lTtX6KZBZA9UNa8tZCZCKe2bBm7l6ZBZCXsZA7lGabAd3jhdViHDdl01vBl5XY04bBD
    Model
        + Created_time: String
        + Message: String
        + id: String
        + is_publish: Boolean
        + status_type: String

curl -i -X GET \
 "https://graph.facebook.com/v14.0/me?fields=feed%7Bid%2Ccreated_time%2Cmessage%2Cis_published%2Cstatus_type%7D&access_token=EAAIVJ8BrFzgBAB6Vb5yjbTNzM5gbS6rMFx4gv5LfOwWYm8L2RdHgMWgFgo6hzNaJFo1eyiZAeBZBbznRSr3mwipLRvjAQJufn6l3XpvVeykPmtoHO73aByM9BWgXKojtUPshmhmBzttZBOCOxixjWZB48BaaB5wsoZCoMwVZBYhwZDZD"
========================
 Android SDK

 GraphRequest request = GraphRequest.newMeRequest(
  accessToken,
  new GraphRequest.GraphJSONObjectCallback() {
    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
      // Insert your code here
    }
});

Bundle parameters = new Bundle();
parameters.putString("fields", "feed{id,created_time,message,is_published,status_type}");
request.setParameters(parameters);
request.executeAsync();
 */
