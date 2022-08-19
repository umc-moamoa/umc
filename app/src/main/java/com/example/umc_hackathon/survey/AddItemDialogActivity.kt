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

        binding.dialogOptionLl.setOnClickListener {
            Log.d("dialogOptionLl", "눌림")
        }

        // 리사이클러뷰 어댑터
        var optionList = arrayListOf<String>()
        val optionRAdapter = OptionRAdapter(optionList)

        binding.dialogItemRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.dialogItemRv.setHasFixedSize(true)
        binding.dialogItemRv.adapter = OptionRAdapter(optionList)

        optionRAdapter.addItem("옵션 1 입니다~~~~~~~")
    }
}