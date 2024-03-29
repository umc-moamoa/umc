package com.example.umc_hackathon.survey

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.DialogOptionItemBinding
import kotlinx.android.synthetic.main.dialog_option_item.*

class DialogOptionItemActivity: AppCompatActivity() {

    private lateinit var binding: DialogOptionItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogOptionItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dialogOptionItemDeleteIv.setOnClickListener {
            Log.d("삭제 버튼", "눌림")
        }
    }

}