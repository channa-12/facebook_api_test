package com.example.facebookapi.screens.facebookDataScreens


import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facebookapi.R
import com.example.facebookapi.databinding.ActivityFacebookPostBinding
import com.example.facebookapi.model.PostDataModel
import com.example.facebookapi.model.PostFeedModel
import com.example.facebookapi.model.PostModel
import com.example.facebookapi.screens.facebookDataScreens.adapter.FacebookPostDetailAdapter
import com.example.facebookapi.screens.facebookDataScreens.common.Common
import com.example.facebookapi.screens.facebookDataScreens.interfaceRetrofit.RetrofitService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.z1Finance.z1FinanceCore.BaseMvpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FacebookPostActivity :
    BaseMvpActivity<FacebookPostView.View, FacebookPostView.Presenter, ActivityFacebookPostBinding>(),
    FacebookPostView.View {

    override var mPresenter: FacebookPostView.Presenter = FacebookPostPresenter()
    override val layoutResource: Int = R.layout.activity_facebook_post

    private lateinit var dbReference: DatabaseReference

    lateinit var mService: RetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: FacebookPostDetailAdapter

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgBack.setOnClickListener {
                    finish()
                }

                mService = Common.retrofitService

                rvFacebookPost.setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@FacebookPostActivity)
                rvFacebookPost.layoutManager = layoutManager
                getAllFacebookPostList()

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun getAllFacebookPostList() {
        binding.apply {
            rvFacebookPost.visibility = View.GONE
            tvLoadingData.visibility = View.VISIBLE
            mService.getFacebookList().enqueue(object : Callback<PostModel> {

                override fun onFailure(call: Call<PostModel>, t: Throwable) {}

                override fun onResponse(
                    call: Call<PostModel>,
                    response: Response<PostModel>
                ) {
                    val postModel: PostModel? = response.body()
                    val postFeed: PostFeedModel? = postModel?.feeds
                    adapter =
                        FacebookPostDetailAdapter(baseContext, postFeed?.dataPost, onClickAdapter)
                    adapter.notifyDataSetChanged()
                    rvFacebookPost.adapter = adapter

                    rvFacebookPost.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            })
        }
    }

    private val onClickAdapter: FacebookPostDetailAdapter.OnClickAdapter =
        object : FacebookPostDetailAdapter.OnClickAdapter {
            override fun onClickMe(postDataModel: PostDataModel) {
                /* path name in dbReference use when fetch data, update, delete from firebase */
                dbReference = FirebaseDatabase.getInstance().getReference("Facebook Posts")
                val postId = postDataModel.postId.toString()
                val postCreatedTime = postDataModel.created_time.toString()
                val postMessage = postDataModel.message.toString()
                val postIsPublish = postDataModel.is_published.toString()
                val postStatusType = postDataModel.status_type.toString()
                val fullPicture = postDataModel.full_picture

                val dbId = postId
                val post = PostDataModel(
                    postId,
                    postCreatedTime,
                    postMessage,
                    postIsPublish,
                    postStatusType,
                    fullPicture
                )

                dbReference.get().addOnSuccessListener {
                    dbReference.child(dbId).setValue(post)
                        .addOnCompleteListener {
                            Toast.makeText(
                                this@FacebookPostActivity,
                                "Data inserted successful.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }.addOnFailureListener { err ->
                            Toast.makeText(
                                this@FacebookPostActivity,
                                "Error ${err.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
        }
}