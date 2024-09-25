package com.example.careurplant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.os.Handler
import android.os.Looper
import kotlin.random.Random
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.careurplant.databinding.FragmentHomeBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.Firebase
import org.w3c.dom.Text



class homeFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var binding : FragmentHomeBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        binding.webView.loadUrl("https://aquaaleart.streamlit.app/")





        return binding.root
    }

}