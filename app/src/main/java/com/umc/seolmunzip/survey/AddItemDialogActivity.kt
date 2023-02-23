package com.umc.seolmunzip.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.umc.seolmunzip.R
import com.umc.seolmunzip.databinding.AddItemDialogBinding

class AddItemDialogActivity : AppCompatActivity() {

    private lateinit var binding: AddItemDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemDialogBinding.inflate(layoutInflater)
        setContentView(R.layout.add_item_dialog)
    }
}