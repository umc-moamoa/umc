package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.MySurvey
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivityParticipatedSurveyBinding

class ParticipatedSurveyActivity : AppCompatActivity(), ParticipatedSurveyView {

    private lateinit var binding: ActivityParticipatedSurveyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipatedSurveyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터
        binding.ppsListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.ppsListRv.setHasFixedSize(true)
        getParticipatedSurvey()

        // 이벤트 리스너
        binding.ppsGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    private fun getParticipatedSurvey() {
        val postService = PostService()
        postService.setParticipatedSurveyView(this)
        postService.getParticipatedSurvey(getJwt().toString())

        Log.d("getParticipatedSurvey", " / ParticipatedActivity에서 메소드")
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    override fun onGetParticipatedSurveySuccess(postList: PostListResponse) {
        binding.ppsListRv.adapter = FormListRAdapter(postList.result)
        Log.d("참여한 설문조사 / ", "참여한 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetParticipatedSurveyFailure() {
        Log.d("참여한 설문조사 / ", "참여한 설문조사 폼 목록을 불러오는데 실패했습니다")
    }

}