package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.FormCreateActivity
import com.example.umc_hackathon.MySurvey
import com.example.umc_hackathon.databinding.ActivityFormListBinding

class FormListActivity : AppCompatActivity(), PostView {

    val TAG: String = "<FormListActivity>"
//    var modelList = ArrayList<MySurvey>()

    val binding = ActivityFormListBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 리스트 생성
//        for (i in 1..10){
//            val mySurvey = MySurvey(title = "사회현상에 대한 소비자 인식 $i")
//            this.modelList.add(mySurvey)
//        }

        binding.rvListItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListItem.setHasFixedSize(true)
        getAllPosts()

        // 이벤트 리스너
        binding.mainButton.setOnClickListener {
            val intent = Intent(this, FormCreateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllPosts() {
        val postService = PostService()
        postService.setPostView(this)

        postService.getAllPost(1) //카테고리는 추후 받아오는 걸로 수정
    }

    override fun onGetAllPostSuccess(postList: List<Post>) {
        binding.rvListItem.adapter = MyRecyclerAdapter(postList)
    }

    override fun onGetPostDetail(post: Post) {
        TODO("Not yet implemented")
    }


}