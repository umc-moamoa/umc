package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.auth.UserInfoResult
import com.example.umc_hackathon.databinding.ActivityMyPointBinding
import com.example.umc_hackathon.databinding.ActivityParticipatedSurveyBinding

class MyPointActivity : AppCompatActivity(), MyPointView {

    private lateinit var binding: ActivityMyPointBinding

    var sortId: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("getMyPoint", "mypointactivity")

        // 어댑터
        binding.myPointPointListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.myPointPointListRv.setHasFixedSize(true)

        // 페이지 이동
        binding.myPointGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        // 정렬 스피너
        val sortList = listOf("최신순", "오래된순")
        binding.myPointSortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                if(pos == 0) {
                    getRecentMyPoint()
                }
                else {
                    getFormerMyPoint()
                }
                println(sortList[pos] + "입니다")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }
        }
    }

    private fun getRecentMyPoint() {
        val postService = PostService()
        postService.setMyPointView(this)
        postService.getRecentMyPoint(getJwt().toString())

        Log.d("getRecentMyPoint", " / MyPointActivity에서 메소드")
    }

    private fun getFormerMyPoint() {
        val postService = PostService()
        postService.setMyPointView(this)
        postService.getFormerMyPoint(getJwt().toString())

        Log.d("getFormerMyPoint", " / MyPointActivity에서 메소드")
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    override fun onGetMyRecentPointSuccess(pointHistoryRecent: MyPointResponse) {
        binding.myPointPointListRv.adapter = MyPointRAdapter(pointHistoryRecent.result.pointHistoryRecent, emptyList())
        Log.d("포인트 내역 최신순 / ", "포인트 내역을 불러오는데 성공했습니다")
    }

    override fun onGetMyFormerPointSuccess(pointHistoryFormer: MyPointResponse) {
        binding.myPointPointListRv.adapter = MyPointRAdapter(emptyList(), pointHistoryFormer.result.pointHistoryFormer)
        Log.d("포인트 내역 오래된순 / ", "포인트 내역을 불러오는데 성공했습니다")
    }

    override fun onGetMyTotalPointSuccess(code: Int, result: MyPointList) {
        binding.myPointTotalTv.text = result.point.toString() + "P"
        Log.d("포인트 / ", "포인트 불러오기에 성공했습니다")
    }

    override fun onGetMyPointFailure() {
        Log.d("포인트 내역 / ", "포인트 내역을 불러오는데 실패했습니다")
    }

}