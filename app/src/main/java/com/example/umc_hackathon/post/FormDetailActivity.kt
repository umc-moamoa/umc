package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_hackathon.auth.AuthService
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding
import com.example.umc_hackathon.databinding.FragmentFormListMarketingBinding
import com.example.umc_hackathon.post.*

class FormDetailActivity : AppCompatActivity(), PostDetailView {

    var postId: Long = 1
    private lateinit var binding: ActivityFormDetailBinding

    val TAG: String = "<FormDetailActivity>"
    private lateinit var binding: ActivityFormDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("list_item_post_id")) {
            postId = intent.getLongExtra("list_item_post_id", postId)
            Log.d("postId", " : " + postId)

            getPostDetail()
        }

        binding.formDetailGoFormListLl.setOnClickListener{
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.formDetailParticipateBtn.setOnClickListener {
            val intent = Intent(this, FormInputActivity::class.java)
            startActivity(intent)
        }

        // 관심 버튼 하트 색깔
    }

    private fun getPostDetail() {
        Log.d("activity/userInfo()", "메소드")

        val postService = PostService()
        postService.setPostDetailView(this)
        postService.getPostDetail(postId, getJwt().toString())
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    override fun onGetPostDetailSuccess(result: PostDetailResult) {
        binding.formDetailTitleTv.text = result.title
        Log.d("PostDetail / ", "상세페이지를 불러오는데 성공했습니다")
    }

    override fun onGetPostDetailFailure() {
        Log.d("PostDetail / ", "상세페이지를 불러오는데 실패했습니다")
    }

}

