package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.auth.UserInfoResult
import com.example.umc_hackathon.databinding.ActivityMyPointBinding
import com.example.umc_hackathon.databinding.ActivityParticipatedSurveyBinding

class MyPointActivity : AppCompatActivity(), MyPointView {

    private lateinit var binding: ActivityMyPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("getMyPoint", "mypointactivity")

        // 어댑터
        binding.myPointPointListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.myPointPointListRv.setHasFixedSize(true)
        getMyPoint()

        // 페이지 이동
        binding.myPointGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }
    }

    private fun getMyPoint() {
        val postService = PostService()
        postService.setMyPointView(this)
        postService.getMyPoint(getJwt().toString())

        Log.d("getMyPoint", " / MyPointActivity에서 메소드")
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    override fun onGetMyPointSuccess(pointHistoryRecent: MyPointResponse) {
        binding.myPointPointListRv.adapter = MyPointRAdapter(pointHistoryRecent.result.pointHistoryRecent)
        Log.d("포인트 내역 / ", "포인트 내역을 불러오는데 성공했습니다")
    }

    override fun onGetMyTotalPointSuccess(code: Int, result: MyPointList) {
        binding.myPointTotalTv.text = result.point.toString() + "P"
        Log.d("포인트 / ", "포인트 불러오기에 성공했습니다")
    }

    override fun onGetMyPointFailure() {
        Log.d("포인트 내역 / ", "포인트 내역을 불러오는데 실패했습니다")
    }

}