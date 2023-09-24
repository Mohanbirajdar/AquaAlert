package com.example.careurplant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.careurplant.Models.User
import com.example.careurplant.Utils.USER_NODE
import com.example.careurplant.Utils.USER_PROFILE_FOLDER
import com.example.careurplant.Utils.uploadImage
import com.example.careurplant.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso

class SignUpPage : AppCompatActivity() {
    val binding by lazy {
        ActivitySignUpPageBinding.inflate(layoutInflater)
    }
    lateinit var user: User
    private val launcher=registerForActivityResult(ActivityResultContracts.GetContent()){
            uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER){
                if (it==null){

                }else{
                    user.image=it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        if (intent.hasExtra("MODE")){
            if (intent.getIntExtra("MODE",-1)==1){
                binding.SignUpBtn.text="Update Profile"
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                    .addOnSuccessListener {
                        user=it.toObject<User>()!!
                        if (!user.image.isNullOrEmpty()){
                            Picasso.get().load(user.image).into(binding.profileImage)
                        }
                        binding.UserName.editText?.setText(user.username)
                        binding.email.editText?.setText(user.email)
                        binding.password.editText?.setText(user.password)
                    }
            }
        }
        binding.SignUpBtn.setOnClickListener {
            if (intent.hasExtra("MODE")){
                if (intent.getIntExtra("MODE",-1)==1){
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this@SignUpPage, "loged in", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this@SignUpPage, HomePage::class.java))
                            finish()
                        }
                }
            }else {
                if (binding.UserName.editText?.text.toString()
                        .equals("") or binding.email.editText?.text.toString()
                        .equals("") or binding.password.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.editText?.text.toString(),
                        binding.password.editText?.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.username = binding.UserName.editText?.text.toString()
                            user.email = binding.email.editText?.text.toString()
                            user.password = binding.password.editText?.text.toString()
                            Firebase.firestore.collection(USER_NODE)
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this@SignUpPage, "loged in", Toast.LENGTH_SHORT)
                                        .show()
                                    startActivity(Intent(this@SignUpPage, HomePage::class.java))
                                    finish()
                                }

                        } else {
                            Toast.makeText(
                                this@SignUpPage,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }

        }
        binding.addImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.login.setOnClickListener{
            startActivity(Intent(this@SignUpPage,LoginActivity::class.java))
            finish()
        }

    }
}