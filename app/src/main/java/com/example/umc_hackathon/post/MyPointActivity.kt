package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.auth.UserInfoResult
import com.example.umc_hackathon.databinding.ActivityMyPageBinding
import com.example.umc_hackathon.databinding.ActivityMyPointBinding
import com.example.umc_hackathon.databinding.ActivityParticipatedSurveyBinding

class MyPointActivity : AppCompatActivity(), RecentMyPointView, FormerMyPointView {

    private lateinit var binding: ActivityMyPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPointBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRecentMyPoint()

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

        val typeList = listOf("최신순", "오래된순")
        binding.myPointSortSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                if(pos == 0) {
                    getRecentMyPoint()
                } else if (pos == 1) {
                    getFormerMyPoint()
                }

                Log.d("포인트 카테고리", typeList[pos])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }

        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun getFormerMyPoint() {
        val postService = PostService()
        postService.setFormerMyPointView(this)
        postService.getFormerMyPoint(getJwt()!!)
    }

    override fun onGetFormerMyPointSuccess(myPointList: MyPointList) {
        binding.myPointPointListRv.adapter = MyPointRAdapter(emptyList(), myPointList.pointHistoryFormer!!)
        Toast.makeText(this, "오래된순 포인트 내역 조회에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onGetFormerMyPointFailure() {
        Toast.makeText(this, "오래된순 포인트 내역 조회에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    private fun getRecentMyPoint() {
        val postService = PostService()
        postService.setRecentMyPointView(this)
        postService.getRecentMyPoint(getJwt()!!)
    }

    override fun onGetRecentMyPointSuccess(myPointList: MyPointList) {
        binding.myPointPointListRv.adapter = MyPointRAdapter(myPointList.pointHistoryRecent!!, emptyList())
        binding.myPointTotalTv.text = myPointList.point.toString() + "P"
        Toast.makeText(this, "최신순 포인트 내역 조회에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onGetRecentMyPointFailure() {
        Toast.makeText(this, "최신순 포인트 내역 조회에 실패했습니다", Toast.LENGTH_SHORT).show()
    }


}