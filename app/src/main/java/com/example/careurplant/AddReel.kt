package com.example.careurplant

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.careurplant.Models.Reel
import com.example.careurplant.Models.User
import com.example.careurplant.Utils.REEL
import com.example.careurplant.Utils.REEL_FOLDER
import com.example.careurplant.Utils.USER_NODE
import com.example.careurplant.Utils.uploadVideo
import com.example.careurplant.databinding.ActivityAddReelBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class AddReel : AppCompatActivity() {
    val binding by lazy {
        ActivityAddReelBinding.inflate(layoutInflater)
    }
    private lateinit var VideoUrl: String
    lateinit var progressDialog:ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER,progressDialog) { url ->
                if (url != null) {


                    VideoUrl = url

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog=ProgressDialog(this)
        binding.SelectVideo.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.cancelbtn.setOnClickListener {
            startActivity(Intent(this@AddReel, HomePage::class.java))
            finish()
        }
        binding.postbtn.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User=it.toObject<User>()!!
                val reel: Reel = Reel(VideoUrl!!, binding.Caption.editText?.text.toString(),user.image!!)
                Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document()
                        .set(reel)
                        .addOnSuccessListener {
                            startActivity(Intent(this@AddReel, HomePage::class.java))
                            finish()
                        }
                }
            }

        }
    }
}
