package com.example.umc_hackathon.survey

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.umc_hackathon.R
import com.example.umc_hackathon.auth.AuthService
import com.example.umc_hackathon.auth.UserInfoResult
import com.example.umc_hackathon.auth.UserInfoView
import com.example.umc_hackathon.databinding.ActivityFormDetailBinding
import com.example.umc_hackathon.databinding.ActivityModifyBinding
import com.example.umc_hackathon.post.PostService

class ModifyActivity : AppCompatActivity(), ModifyView {

    private lateinit var binding: ActivityModifyBinding

    private var postId: Long = 0L
    private lateinit var postTitle: String
    private lateinit var postDeadline: String
    private lateinit var postContent: String
    private var postUserId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("postId")) {
            postId = intent.getLongExtra("postId", postId)
        }

        if(intent.hasExtra("postUserId")) {
            postUserId = intent.getLongExtra("postUserId", postUserId)
        }

        if(intent.hasExtra("postTitle")) {
            postTitle = intent.getStringExtra("postTitle")!!
            binding.formModifyTitleTv.setText(postTitle)
        }

        postDeadline = "2022-10-20"
        binding.formModifyDeadlineTv.setText(postDeadline)
//        if(intent.hasExtra("postDeadline")) {
//            postDeadline = intent.getLongExtra("postDeadline", postDeadline)
//            binding.formModifyDeadlineTv.setText("$postDeadline\n 0000-00-00 형식으로 마감일을 적어주세요")
//        }

        if(intent.hasExtra("postContent")) {
            postContent = intent.getStringExtra("postContent")!!
            binding.formModifyInfoTv.setText(postContent)
        }

        binding.formDetailModifyBtn.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("설문을 수정하시겠습니까?")
                setPositiveButton("네, 수정하겠습니다", DialogInterface.OnClickListener { dialogInterface, i ->
                   modifyForm()
                })
                setNegativeButton("아니요", null)
                show()
            }
        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun getModifiedForm(): ModifyRequest {
        val formTitle: String = binding.formModifyTitleTv.text.toString()
        val formContent: String = binding.formModifyInfoTv.text.toString()
        val formDeadline: String = binding.formModifyDeadlineTv.text.toString()

        return ModifyRequest(postId, formTitle, formContent, formDeadline, postUserId)
    }

    private fun modifyForm() {
        val formService = FormService()
        formService.setModifyView(this)
        formService.formModify(getModifiedForm(), getJwt().toString())
    }

    override fun onFormModifySuccess(modifyResponse: ModifyResponse) {
        Toast.makeText(this, "설문 수정을 성공했습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onFormModifyFailure() {
        Toast.makeText(this, "설문 수정을 실패했습니다.", Toast.LENGTH_SHORT).show()
    }


}