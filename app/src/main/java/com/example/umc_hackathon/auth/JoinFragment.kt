package com.example.umc_hackathon.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.FragmentJoinBinding
import com.example.umc_hackathon.databinding.FragmentLoginBinding

class JoinFragment : Fragment() {

    private lateinit var binding: FragmentJoinBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentJoinBinding.inflate(layoutInflater)

        return binding.root
    }

}