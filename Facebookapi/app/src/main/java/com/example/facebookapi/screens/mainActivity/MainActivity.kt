package com.example.facebookapi.screens.mainActivity


import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.facebookapi.R
import com.example.facebookapi.databinding.ActivityMainBinding
import com.example.facebookapi.model.PostDataModel
import com.example.facebookapi.screens.facebookDataScreens.FacebookPostActivity
import com.example.facebookapi.screens.mainActivity.adapter.FirebaseDataAdapter
import com.example.facebookapi.screens.postEditActivity.PostEditActivity
import com.google.firebase.database.*
import com.z1Finance.z1FinanceCore.BaseMvpActivity

class MainActivity :
    BaseMvpActivity<MainView.View, MainView.Presenter, ActivityMainBinding>(),
    MainView.View {

    override var mPresenter: MainView.Presenter = MainPresenter()
    override val layoutResource: Int = R.layout.activity_main

    private lateinit var dbReference: DatabaseReference
    private var mAdapter: FirebaseDataAdapter? = null
    private lateinit var firebasePostList: ArrayList<PostDataModel>

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                addFacebookPost.setOnClickListener {
                    val intent = Intent(this@MainActivity, FacebookPostActivity::class.java)
                    startActivity(intent)
                }

                rvFirebaseData.layoutManager = LinearLayoutManager(this@MainActivity)
                rvFirebaseData.setHasFixedSize(true)

                firebasePostList = arrayListOf<PostDataModel>()
                dbReference = FirebaseDatabase.getInstance().getReference("Facebook Posts")
                getEmployeeModel()

                searchMessageName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (char != null) {
                            getSearchData(char)
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {}
                })
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onClickAdapter: FirebaseDataAdapter.OnClickAdapter =
        object : FirebaseDataAdapter.OnClickAdapter {
            override fun onClickMe(postDataModel: PostDataModel) {
                val intent = Intent(this@MainActivity, PostEditActivity::class.java)

                // put extra
                intent.putExtra("postId", postDataModel.postId)
                intent.putExtra("message", postDataModel.message)
                intent.putExtra("status_type", postDataModel.status_type)
                intent.putExtra("created_time", postDataModel.created_time)
                intent.putExtra("is_published", postDataModel.is_published)
                intent.putExtra("full_picture", postDataModel.full_picture)
                startActivity(intent)

            }
        }

    private fun getEmployeeModel() {
        binding.apply {

            rvFirebaseData.visibility = View.GONE
            tvLoadingData.visibility = View.VISIBLE
            txtNoData.visibility = View.GONE

            dbReference = FirebaseDatabase.getInstance().getReference("Facebook Posts")

            dbReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    firebasePostList.clear()
                    if (snapshot.exists()) {
                        for (postSnap in snapshot.children) {
                            val postData = postSnap.getValue(PostDataModel::class.java)
                            firebasePostList.add(postData!!)
                        }
                        mAdapter = FirebaseDataAdapter(firebasePostList, onClickAdapter)
                        rvFirebaseData.adapter = mAdapter

                        rvFirebaseData.visibility = View.VISIBLE
                        tvLoadingData.visibility = View.GONE
                    }
                    if (firebasePostList.isEmpty()) {
                        txtNoData.visibility = View.VISIBLE
                        tvLoadingData.visibility = View.GONE
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

    private fun getSearchData(char: CharSequence) {

        val newArray = arrayListOf<PostDataModel>()
        binding.apply {
            if (char.isNotEmpty()) {
                newArray.clear()
                firebasePostList.map {
                    if (it.message?.contains(char, ignoreCase = true) == true) {
                        newArray.add(it)
                    }
                }
            } else {
                newArray.addAll(firebasePostList)
            }
            mAdapter = FirebaseDataAdapter(newArray, onClickAdapter)
            rvFirebaseData.adapter = mAdapter
        }
    }
}