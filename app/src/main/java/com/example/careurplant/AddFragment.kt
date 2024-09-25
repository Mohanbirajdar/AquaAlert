package com.example.careurplant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.careurplant.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddBinding.inflate(inflater,container,false)
        binding.Createpost.setOnClickListener{
            activity?.startActivity(Intent(requireContext(),Addpost::class.java))
            activity?.finish()

        }
        binding.AddReel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),AddReel::class.java))
            activity?.finish()

        }
        return binding.root
    }

    companion object {

    }
}