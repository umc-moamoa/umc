package com.example.umc_hackathon.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.AddItemDialogBinding

class AddItemDialogActivity : AppCompatActivity() {

    private lateinit var binding: AddItemDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemDialogBinding.inflate(layoutInflater)
        setContentView(R.layout.add_item_dialog)


    }
}