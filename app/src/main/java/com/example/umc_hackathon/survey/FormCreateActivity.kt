package com.example.umc_hackathon.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.MyQuestion
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.post.FormListActivity

class FormCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스피너
        val categoryList = listOf("마케팅", "사회현상", "브랜드", "아이디어")
        binding.formCreateSelectCategorySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categoryList)
        binding.formCreateSelectCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                println(categoryList[p2] + "입니다")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }
        }

        // 이벤트 리스너
        binding.formCreateCancelTv.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}