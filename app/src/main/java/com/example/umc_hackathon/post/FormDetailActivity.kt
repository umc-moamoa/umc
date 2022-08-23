package com.example.umc_hackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.umc_hackathon.auth.LoginActivity
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding
import com.example.umc_hackathon.post.*
import com.example.umc_hackathon.post.result.ResultActivity
import com.example.umc_hackathon.post.result.ResultFragment
import com.example.umc_hackathon.survey.FormInputActivity

class FormDetailActivity : AppCompatActivity(), PostDetailView {

    private var postId: Long = 0L
    private lateinit var postTitle: String
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
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            finish()
        }

        binding.formDetailParticipateBtn.setOnClickListener {
            val intent = Intent(this, FormInputActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.formDetailLikeBtnCv.setOnClickListener {
            dislikePost()
        }
        binding.formDetailDislikeBtnCv.setOnClickListener {
            likePost()
        }
        binding.formDetailDeleteBtn.setOnClickListener {
            deletePost()
        }
        binding.formDetailResultBtn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("postId", postId)
            intent.putExtra("postTitle", postTitle)
            startActivity(intent)
            finish()
        }
    }

    private fun getPostDetail() {
        Log.d("activity/userInfo()", "메소드")

        val postService = PostService()
        postService.setPostDetailView(this)
        postService.getPostDetail(postId, getJwt().toString())
    }

    private fun likePost() {
        val postService = PostService()
        postService.setPostDetailView(this)
        postService.likePost(postId, getJwt().toString())
    }

    private fun dislikePost() {
        val postService = PostService()
        postService.setPostDetailView(this)
        postService.dislikePost(postId, getJwt().toString())
    }

    private fun deletePost() {
        val postService = PostService()
        postService.setPostDetailView(this)
        postService.deletePost(postId, getJwt().toString())
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    override fun onGetPostDetailSuccess(result: PostDetailResult) {
        postTitle = result.title
        binding.formDetailTitleTv.text = postTitle
        binding.formDetailItemCountTv.text = result.qCount.toString() + "개의 항목"

        if (result.status == "ACTIVE") {
            if (result.dday == 0) {
                binding.formDetailItemDeadlineTv.text = "D - DAY"
            }
            else {
                binding.formDetailItemDeadlineTv.text = "D - " + result.dday.toString()
            }
        }
        else {
            binding.formDetailItemDeadlineTv.text = "마감"
        }

        if(result.myPost) {
            Log.d("mypost", result.myPost.toString())
            binding.formDetailParticipateBtn.visibility = View.INVISIBLE
            binding.formDetailResultBtn.visibility = View.VISIBLE
            binding.formDetailDislikeBtnCv.visibility = View.INVISIBLE
            binding.formDetailLikeBtnCv.visibility = View.INVISIBLE
            binding.formDetailDeleteBtn.visibility = View.VISIBLE
        }
        else {
            if(result.like) {
                Log.d("like", result.like.toString())
                binding.formDetailLikeBtnCv.visibility = View.VISIBLE
                binding.formDetailDislikeBtnCv.visibility = View.INVISIBLE
            }
            else {
                Log.d("dislike", result.like.toString())
                binding.formDetailLikeBtnCv.visibility = View.INVISIBLE
                binding.formDetailDislikeBtnCv.visibility = View.VISIBLE
            }
        }
        Log.d("PostDetail / ", "상세페이지를 불러오는데 성공했습니다")
    }

    override fun onGetPostDetailFailure() {
        Log.d("PostDetail / ", "상세페이지를 불러오는데 실패했습니다")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onLikeSuccess() {
        binding.formDetailLikeBtnCv.visibility = View.VISIBLE
        binding.formDetailDislikeBtnCv.visibility = View.INVISIBLE
    }

    override fun onLikeFailure(result: LikeResponse) {
        Log.d("getLikePost()", " 실패 / " + result.message)
    }

    override fun onDislikeSuccess() {
        binding.formDetailLikeBtnCv.visibility = View.INVISIBLE
        binding.formDetailDislikeBtnCv.visibility = View.VISIBLE
    }

    override fun onDislikeFailure(result: StringResultResponse) {
        Log.d("dislikePost()", " 실패 / " + result.message)
    }

    override fun onDeleteSuccess() {
        //삭제 알림창 띄우기
    }

    override fun onDeleteFailure(result: StringResultResponse) {
        Log.d("deletePost()", " 실패 / " + result.message)
    }

}

