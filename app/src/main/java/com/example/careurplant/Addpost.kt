package com.example.careurplant

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.careurplant.Models.Post
import com.example.careurplant.Utils.POST
import com.example.careurplant.Utils.POST_FOLDER
import com.example.careurplant.Utils.USER_PROFILE_FOLDER
import com.example.careurplant.Utils.uploadImage
import com.example.careurplant.databinding.ActivityAddpostBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class Addpost : AppCompatActivity() {
    val binding by lazy {
        ActivityAddpostBinding.inflate(layoutInflater)
    }
    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {

                    binding.AddPost.setImageURI(uri)
                    imageUrl = url

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@Addpost,HomePage::class.java))
            finish()
        }

        binding.AddPost.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancelbtn.setOnClickListener{
            startActivity(Intent(this@Addpost,HomePage::class.java))
            finish()
        }
        binding.postbtn.setOnClickListener {
            val post: Post = Post(imageUrl!!, binding.Caption.editText?.text.toString())
            Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
                    .addOnSuccessListener {
                        startActivity(Intent(this@Addpost, HomePage::class.java))
                        finish()
                    }
            }
        }

    }
}
