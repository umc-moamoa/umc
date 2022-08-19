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

class MainActivity : AppCompatActivity(), WaitingSurveyView, PopularSurveyView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터
        binding.mainWaitingSurveyListRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.mainWaitingSurveyListRv.setHasFixedSize(true)
        getWaitingSurvey()

        // 이벤트 리스너
        binding.mainProfileIv.setOnClickListener {
            if (getJwt().isNullOrBlank()) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            } else {
                val intent = Intent(this, MyPageActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }

        binding.mainPopularSurveyLl.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.mainWaitingSurveyListHeaderLl.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // 스크롤뷰 시작 위치 상단으로 고정하기
        binding.mainSv.post {
            binding.mainSv.fullScroll(ScrollView.FOCUS_UP)
        }

        // 회원 구별 & 웰컴 메시지
        if (getNickName() == "" || getNickName() == null) {
            binding.mainWelcomeTv.text = "로그인이 필요합니다"
        } else {
            binding.mainWelcomeTv.text = getNickName() + "님! 반갑습니다"
        }

        // 인기있는 설문조사 조회
        getPopularSurvey()
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

    private fun getPopularSurvey() {
        val postService = PostService()
        postService.setPopularSurveyView(this)
        postService.getPopularSurvey()
    }

    private fun getWaitingSurvey() {
        val postService = PostService()
        postService.setWaitingSurveyView(this)
        postService.getWaitingSurvey()
    }

    override fun onGetPopularSurveySuccess(postList: PostListResponse) {
        // 무지성 코드 죄송^^ 나중에 수정하겠음
        binding.mainPopularFirstTitleTv.text = postList.result[0].title
        binding.mainPopularFirstCountTv.text = postList.result[0].qcount.toString() + "개의 항목"
        if(postList.result[0].dday == 0) {
            binding.mainPopularFirstDeadlineTv.text = "D - DAY"
        } else {
            binding.mainPopularFirstDeadlineTv.text = "D - " + postList.result[0].dday.toString()
        }
        binding.mainPopularFirstPointTv.text = postList.result[0].point.toString() + "P"

        binding.mainSecondTitleTv.text = postList.result[1].title
        binding.mainSecondCountTv.text = postList.result[1].qcount.toString() + "개의 항목"
        if(postList.result[1].dday == 0) {
            binding.mainSecondDeadlineTv.text = "D - DAY"
        } else {
            binding.mainSecondDeadlineTv.text = "D - " + postList.result[1].dday.toString()
        }
        binding.mainSecondPointTv.text = postList.result[1].point.toString() + "P"

        binding.mainThirdTitleTv.text = postList.result[2].title
        binding.mainThirdCountTv.text = postList.result[2].qcount.toString() + "개의 항목"
        if(postList.result[2].dday == 0) {
            binding.mainThirdDeadlineTv.text = "D - DAY"
        } else {
            binding.mainThirdDeadlineTv.text = "D - " + postList.result[2].dday.toString()
        }
        binding.mainThirdPointTv.text = postList.result[2].point.toString() + "P"

        Log.d("인기있는 설문조사 / ", "MainActivity, 인기있는 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetPopularSurveyFailure() {
        Log.d("인기있는 설문조사 / ", "MainActivity, 인기있는 설문조사 폼 목록을 불러오는데 실패했습니다")
    }

    override fun onGetWaitingSurveySuccess(postList: PostListResponse) {
        binding.mainWaitingSurveyListRv.adapter = WaitingSurveyListRAdapter(postList.result)
        Log.d("참여를 기다리는 설문조사 / ", "MainActivity, 참여를 기다리는 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetWaitingSurveyFailure() {
        Log.d("참여를 기다리는 설문조사 / ", "MainActivity, 참여를 기다리는 설문조사 폼 목록을 불러오는데 실패했습니다")
    }
}