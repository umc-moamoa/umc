package com.example.umc_hackathon.post.detail

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.umc_hackathon.auth.AuthActivity
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding
import com.example.umc_hackathon.post.PostService
import com.example.umc_hackathon.post.StringResultResponse
import com.example.umc_hackathon.post.LikeResponse
import com.example.umc_hackathon.survey.result.ResultActivity
import com.example.umc_hackathon.survey.participate.FormInputActivity
import com.example.umc_hackathon.post.modify.ModifyActivity
import com.example.umc_hackathon.my.survey.MyAnswerActivity

class PostDetailActivity : AppCompatActivity(), PostDetailView {

    private final var TAG = "FormDetailActivity"
    private var postId: Long = 0L
    private lateinit var postTitle: String
    private var postDeadline: Int = 0
    private var postUserId: Long = 0
    private lateinit var postContent: String
    private lateinit var binding: ActivityFormDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("list_item_post_id")) {
            postId = intent.getLongExtra("list_item_post_id", postId)
            Log.d(TAG, "postId : $postId")
            getPostDetail()
        }

        binding.formDetailGoFormListLl.setOnClickListener{
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            finish()
        }

        binding.formDetailParticipateBtn.setOnClickListener {
            val intent = Intent(this, FormInputActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("postId", postId)
            startActivity(intent)
            finish()
        }

        binding.formDetailMyAnswerBtn.setOnClickListener {
            val intent = Intent(this, MyAnswerActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("postId", postId)
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
            AlertDialog.Builder(this).run {
                setTitle("설문을 삭제하시겠습니까?")
                setPositiveButton("네, 삭제하겠습니다", DialogInterface.OnClickListener { dialogInterface, i ->
                    deletePost()
                })
                setNegativeButton("아니요", null)
                show()
            }
        }

        binding.formDetailResultBtn.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("postId", postId)
            intent.putExtra("postTitle", postTitle)
            startActivity(intent)
            finish()
        }

        binding.formDetailModifyBtn.setOnClickListener {
            val intent = Intent(this, ModifyActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("postId", postId)
            intent.putExtra("postTitle", postTitle)
            intent.putExtra("postContent", postContent)
            intent.putExtra("postDeadline", postDeadline)
            intent.putExtra("postUserId", postUserId)
            startActivity(intent)
            finish()
        }

        binding.formDetailShareCv.setOnClickListener {
            val postService = PostService()
            postService.setPostDetailView(this)
            postService.getShareLink(postId, getAccessToken().toString(), getRefreshToken().toString())
        }
    }

    private fun getPostDetail() {
        Log.d("activity/userInfo()", "메소드")

        val postService = PostService()
        postService.setPostDetailView(this)
        postService.getPostDetail(postId, getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun likePost() {
        val postService = PostService()
        postService.setPostDetailView(this)
        postService.likePost(postId, getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun dislikePost() {
        val postService = PostService()
        postService.setPostDetailView(this)
        postService.dislikePost(postId, getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun deletePost() {
        val postService = PostService()
        postService.setPostDetailView(this)
        postService.deletePost(postId, getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    override fun onGetPostDetailSuccess(result: PostDetailResult) {
        postTitle = result.title
        postDeadline = result.dday
        postContent = result.content
        postUserId = result.postUserId

        binding.formDetailTitleTv.text = postTitle
        binding.formDetailInfoTv.text = result.content
        binding.formDetailItemCountTv.text = result.qCount.toString() + "개의 항목"

        if (result.status == "ACTIVE") {
//            binding.formDetailModifyBtn.visibility = View.VISIBLE
            binding.formDetailParticipateBtn.visibility = View.VISIBLE

            if (result.dday == 0) {
                binding.formDetailItemDeadlineTv.text = "D - DAY"
            } else {
                binding.formDetailItemDeadlineTv.text = "D - " + result.dday.toString()
            }
        }
        else {
            binding.formDetailItemDeadlineTv.text = "마감"
            binding.formDetailParticipateBtn.visibility = View.INVISIBLE
        }

        Log.d("My Post", result.myPost.toString())

        if(result.myPost) {
            binding.formDetailParticipateBtn.visibility = View.INVISIBLE
            binding.formDetailResultBtn.visibility = View.VISIBLE
            binding.formDetailDislikeBtnCv.visibility = View.VISIBLE
            binding.formDetailLikeBtnCv.visibility = View.INVISIBLE
            binding.formDetailDeleteBtn.visibility = View.VISIBLE
            binding.formDetailModifyBtn.visibility = View.VISIBLE
        }
        else {
            binding.formDetailModifyBtn.visibility = View.INVISIBLE

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

            // status == closed일 때 button 비활성화
            if(result.status == "CLOSED") {
                binding.formDetailParticipateBtn.isEnabled = false
            }
        }

        if(result.participation) {
            binding.formDetailMyAnswerBtn.visibility = View.VISIBLE
            binding.formDetailModifyBtn.visibility = View.INVISIBLE

            if(result.myPost) {
                binding.formDetailMyAnswerBtn.visibility = View.INVISIBLE
            }
        }

        Log.d("PostDetail / ", "상세페이지를 불러오는데 성공했습니다")
    }

    override fun onGetPostDetailFailure(result: PostDetailResponse) {
        Log.d("PostDetail / ", "상세페이지를 불러오는데 실패했습니다" + result.code)

        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
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
        Toast.makeText(this, "설문 삭제를 성공했습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteFailure(result: StringResultResponse) {
        Toast.makeText(this, "설문 삭제를 실패했습니다.", Toast.LENGTH_SHORT).show()
        Log.d("deletePost()", " 실패 / " + result.message)
    }

    override fun onGetShareLinkSuccess(result: String) {
        Toast.makeText(this, "설문 링크를 클립보드에 복사하였습니다.", Toast.LENGTH_SHORT).show()
        Log.d("shareLink()", result)

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        // Creates a new text clip to put on the clipboard
        val clip: ClipData = ClipData.newPlainText("seolmunzip-url", result)

        // Set the clipboard's primary clip.
        clipboard.setPrimaryClip(clip)
    }

    override fun onGetShareLinkFailure() {
        Toast.makeText(this, "설문 링크가 유효하지 않습니다.", Toast.LENGTH_SHORT).show()
    }

}

