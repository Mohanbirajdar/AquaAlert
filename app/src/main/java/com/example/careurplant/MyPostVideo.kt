package com.example.careurplant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.careurplant.Models.Post
import com.example.careurplant.Models.Reel
import com.example.careurplant.Utils.REEL
import com.example.careurplant.adapters.MyPostRvAdapter
import com.example.careurplant.adapters.MyReelAdapter
import com.example.careurplant.databinding.ActivityAddReelBinding
import com.example.careurplant.databinding.FragmentMyPostVideoBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class MyPostVideo : Fragment() {
    private lateinit var binding: FragmentMyPostVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding=FragmentMyPostVideoBinding.inflate(inflater, container, false)
        var reelList = ArrayList<Reel>()
        var adapter = MyReelAdapter(requireContext(), reelList)
        binding.rv.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ REEL).get().addOnSuccessListener {
            var tempList = ArrayList<Reel>()
            for (i in it.documents)
                for (i in it.documents) {
                    var reel: Reel = i.toObject<Reel>()!!
                    tempList.add(reel)
                }
            reelList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }
}

