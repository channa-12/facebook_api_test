package com.example.facebookapi.screens.mainActivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebookapi.FacebookAPI
import com.example.facebookapi.R
import com.example.facebookapi.model.PostDataModel
import com.squareup.picasso.Picasso

class FirebaseDataAdapter (private var listData: List<PostDataModel>?, onClickAdapter: OnClickAdapter) : RecyclerView.Adapter<FirebaseDataAdapter.ViewHolder>() {

    private var onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtPostId: TextView = view.findViewById<View>(R.id.txt_post_id_fb) as TextView
        var txtMessage: TextView = view.findViewById<View>(R.id.txt_message_fb) as TextView
        var txtStatusType: TextView = view.findViewById<View>(R.id.txt_status_type_fb) as TextView
        var imageFullPicture: ImageView = view.findViewById<View>(R.id.img_full_picture_fb) as ImageView
        var txtIsPublished: TextView = view.findViewById<View>(R.id.txt_is_published_fb) as TextView
        var txtCreatedTime: TextView = view.findViewById<View>(R.id.txt_created_time_fb) as TextView
        var btnEdit: LinearLayout = view.findViewById<View>(R.id.btn_edit_fb) as LinearLayout
        var layItemRecyclerView: CardView = view.findViewById(R.id.lay_item) as CardView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FirebaseDataAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_firebase_data, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: FirebaseDataAdapter.ViewHolder, position: Int) {
        val firebaseDataList: PostDataModel = listData!![position]
        holder.txtPostId.text = firebaseDataList.postId
        holder.txtMessage.text = firebaseDataList.message
        holder.txtStatusType.text = firebaseDataList.status_type
        Picasso.get().load(firebaseDataList.full_picture).into(holder.imageFullPicture);
        holder.txtIsPublished.text = firebaseDataList.is_published
        holder.txtCreatedTime.text = firebaseDataList.created_time
        holder.btnEdit.setOnClickListener {
            onClickAdapter.onClickMe(firebaseDataList)
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