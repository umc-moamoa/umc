package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.FormCreateActivity
import com.example.umc_hackathon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = "<MainActivity>"
//    var postList = ArrayList<MySurvey>()
    val binding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainWaitingSurveyListSeeMoreTv.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
        }
    }

}