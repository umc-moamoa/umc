package com.example.umc_hackathon.my.point

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.auth.*
import com.example.umc_hackathon.auth.dto.ReAccessTokenResponse
import com.example.umc_hackathon.auth.view.ReAccessTokenView
import com.example.umc_hackathon.databinding.ActivityMyPointBinding
import com.example.umc_hackathon.my.CustomDialog
import com.example.umc_hackathon.my.MyPageActivity
import com.example.umc_hackathon.post.PostService

class MyPointActivity : AppCompatActivity(), MyPointView, ReAccessTokenView {

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

        binding.myPointInfo.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.pointInfoViews()
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
        postService.getRecentMyPoint(getAccessToken().toString(), getRefreshToken().toString())

        Log.d("getRecentMyPoint", " / MyPointActivity에서 메소드")
    }

    private fun getFormerMyPoint() {
        val postService = PostService()
        postService.setMyPointView(this)
        postService.getFormerMyPoint(getAccessToken().toString(), getRefreshToken().toString())

        Log.d("getFormerMyPoint", " / MyPointActivity에서 메소드")
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    private fun getReAccessToken() {
        val authService = AuthService()
        authService.setReAccessTokenView(this)
        authService.getReAccessToken(getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun saveAccessToken(accessToken: String) {
        val spf = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("accessToken", accessToken)
        editor.apply()

        Log.d("엑세스토근", "세이브")
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

    override fun onGetMyPointFailure(result: MyPointResponse) {
        Log.d("포인트 내역 / ", "포인트 내역을 불러오는데 실패했습니다" + result.code)

        if(result.code == 2002) {
            getReAccessToken()
        }
    }

    override fun onGetReAccessTokenSuccess(res: ReAccessTokenResponse) {
        Log.d("액세스 토큰", "재발급했습니다.")
        saveAccessToken(res.result)
    }

    override fun onGetReAccessTokenFailure() {
        Log.d("액세스 토큰", "재발급 실패했습니다.")
    }

}