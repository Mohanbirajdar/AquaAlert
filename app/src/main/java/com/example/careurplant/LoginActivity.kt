package com.example.careurplant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.careurplant.Models.User
import com.example.careurplant.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener{
            if (binding.Email.editText?.text.toString()
                    .equals("") or binding.password.editText?.text.toString().equals("")
            ){
                Toast.makeText(this@LoginActivity, "Please fill all the information ", Toast.LENGTH_SHORT).show()
            }else{
                var user=User(binding.Email.editText?.text.toString(),binding.password.editText?.text.toString())
                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this@LoginActivity,HomePage::class.java))
                        finish()

                    }else{
                        Toast.makeText(this@LoginActivity, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        binding.login.setOnClickListener{
            startActivity(Intent(this@LoginActivity,SignUpPage::class.java))
            finish()
        }
    }
}