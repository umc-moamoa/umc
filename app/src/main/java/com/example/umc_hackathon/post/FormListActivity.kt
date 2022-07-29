package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.FormCreateActivity
import com.example.umc_hackathon.FormListRAdapter
import com.example.umc_hackathon.MySurvey
import com.example.umc_hackathon.databinding.ActivityFormListBinding

class FormListActivity : AppCompatActivity(), PostView {

    val TAG: String = "<FormListActivity>"

    private lateinit var binding : ActivityFormListBinding
    //private lateinit var postList : List<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.formListListItemRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formListListItemRv.setHasFixedSize(true)
        getAllPosts()
        //binding.formListListItemRv.adapter = FormListRAdapter(postList)

        // 이벤트 리스너
        binding.formListFormCreateBtn.setOnClickListener {
            val intent = Intent(this, FormCreateActivity::class.java)
            startActivity(intent)
        }

        binding.formListGoMainLl.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getAllPosts() {
        val postService = PostService()
        postService.setPostView(this)

        postService.getAllPost(1) //카테고리는 추후 받아오는 걸로 수정
    }

    override fun onGetAllPostSuccess(postList: PostListResponse) {
//        this.postList = postList
        binding.formListListItemRv.adapter = FormListRAdapter(postList.result)
    }

    override fun onGetPostDetail(post: Post) {
        TODO("Not yet implemented")
    }


}