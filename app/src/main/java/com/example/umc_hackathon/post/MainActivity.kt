package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.LoginActivity
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivityMainBinding
import com.example.umc_hackathon.post.*

class MainActivity : AppCompatActivity(), PostListView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터
        binding.mainWaitingSurveyListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainWaitingSurveyListRv.setHasFixedSize(true)
        getPostList(1)

        // 이벤트 리스너
        binding.mainProfileIv.setOnClickListener {
            if(getJwt().isNullOrBlank()) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(this, MyPageActivity::class.java)
                startActivity(intent)
            }
        }

        binding.mainPopularSurveyLl.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
        }

        binding.mainWaitingSurveyListHeaderLl.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
        }

        // 스크롤뷰 시작 위치 상단으로 고정하기
        binding.mainSv.post {
            binding.mainSv.fullScroll(ScrollView.FOCUS_UP)
        }

        // 회원 인사
        if(getNickName() == "" || getNickName() == null) {
            binding.mainWelcomeTv.text = "로그인이 필요합니다"
        } else {
            binding.mainWelcomeTv.text = getNickName() + "님! 반갑습니다"
        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun getNickName(): String? {
        val spf = this.getSharedPreferences("nickName", AppCompatActivity.MODE_PRIVATE)
        Log.d("nickName: ", "" + spf!!.getString("nickName", ""))
        return spf!!.getString("nickName", "")
    }

    private fun getPostList(category: Long) {
        val postService = PostService()
        postService.setPostListView(this)
        postService.getPostList(category)
    }

    override fun onGetPostListSuccess(postList: PostListResponse) {
        binding.mainWaitingSurveyListRv.adapter = WaitingSurveyListRAdapter(postList.result)
        Log.d("참여를 기다리는 설문조사 / ", "MainActivity, 참여를 기다리는 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetPostListFailure() {
        Log.d("참여를 기다리는 설문조사 / ", "MainActivity, 참여를 기다리는 설문조사 폼 목록을 불러오는데 실패했습니다")
    }

}