package com.example.facebookapi.screens.postEditActivity

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.facebookapi.R
import com.example.facebookapi.databinding.ActivityPostEditBinding
import com.example.facebookapi.model.PostDataModel
import com.example.facebookapi.screens.mainActivity.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.z1Finance.z1FinanceCore.BaseMvpActivity


class PostEditActivity :
    BaseMvpActivity<PostEditView.View, PostEditView.Presenter, com.example.facebookapi.databinding.ActivityPostEditBinding>(),
    PostEditView.View {

    override var mPresenter: PostEditView.Presenter = PostEditPresenter()
    override val layoutResource: Int = R.layout.activity_post_edit

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgBack.setOnClickListener {
                    finish()
                }

                setValueToViews()

                btnEditFb.setOnClickListener {
                    openUpdateDialog(
                        intent.getStringExtra("postId").toString(),
                        intent.getStringExtra("message").toString()
                    )
                }

                btnDeleteFb.setOnClickListener{
                    deleteRecord(
                        intent.getStringExtra("postId").toString()
                    )
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun setValueToViews() {
        binding.apply {
            txtPostIdEdit.text = intent.getStringExtra("postId")
            txtMessageEdit.text = intent.getStringExtra("message")
            txtStatusTypeEdit.text = intent.getStringExtra("status_type")
            txtCreatedTimeEdit.text = intent.getStringExtra("created_time")
            txtIsPublishedEdit.text = intent.getStringExtra("is_published")
            Picasso.get().load(intent.getStringExtra("full_picture")).into(imgFullPictureEdit)
        }
    }

    private fun openUpdateDialog(
        postId: String,
        message: String,
    ) {
        val editDialog = AlertDialog.Builder(this@PostEditActivity)
        val inflater = layoutInflater
        val editDialogView = inflater.inflate(R.layout.update_post_dialog, null)
        editDialog.setView(editDialogView)

        val txtPostId = editDialogView.findViewById<TextView>(R.id.txt_post_id_edit)
        val etDescription = editDialogView.findViewById<EditText>(R.id.ed_description)
        val etStatusType = editDialogView.findViewById<EditText>(R.id.ed_status_type)
        val btnEdited = editDialogView.findViewById<LinearLayout>(R.id.btn_edit_post_fb)

        txtPostId.text = intent.getStringExtra("postId").toString()
        etDescription.setText(intent.getStringExtra("message").toString())
        etStatusType.setText(intent.getStringExtra("status_type").toString())

//            editDialog.setTitle("Updating $message Record")

        val alertDialog = editDialog.create()
        alertDialog.show()

        btnEdited.setOnClickListener {
            updatePostData(
                postId,
                etDescription.text.toString(),
                etStatusType.text.toString()
            )
            alertDialog.dismiss()
        }
    }

    private fun updatePostData(
        postId: String,
        message: String,
        status_type: String,
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Facebook Posts")
        //.child(postId)
        // val postInfo = PostDataModel(postId, message, status_type)
        // dbRef.setValue(postInfo)
        Toast.makeText(this@PostEditActivity,"Updated Post",Toast.LENGTH_SHORT).show()
        dbRef.get().addOnSuccessListener {
            if (it.exists()) {
                for (ds in it.children) {
                    if (postId == ds.child("postId").value.toString()) {
                        dbRef.child(postId).child("message").setValue(message)
                        dbRef.child(postId).child("status_type").setValue(status_type)
                        break
                    }
                }
            }
        }
        // we are setting updated data to our textview
        binding.txtMessageEdit.text = message
        binding.txtStatusTypeEdit.text = status_type
    }

    private fun deleteRecord(
        postId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Facebook Posts").child(postId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this@PostEditActivity, "Post has been deleted!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@PostEditActivity, MainActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this@PostEditActivity, "Deleting Error ${error.message}", Toast.LENGTH_SHORT).show()
        }


    }
}
