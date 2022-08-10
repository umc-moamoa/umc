package com.example.umc_hackathon.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.MySurvey
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.FragmentFormListBrandBinding
import com.example.umc_hackathon.temporary.WaitingSurveyListRAdapter
import com.example.umc_hackathon.databinding.FragmentFormListIdeaBinding

class FormListIdea : Fragment(), PostListView {

    private val categoryId: Long = 1 // 추후에 값 바꿀 것
    private lateinit var binding: FragmentFormListIdeaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFormListIdeaBinding.inflate(layoutInflater)

        binding.fragmentIdeaRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentIdeaRv.setHasFixedSize(true)
        getPostList(categoryId)

        return binding.root
    }

    private fun getPostList(category: Long) {
        val postService = PostService()
        postService.setPostListView(this)
        postService.getPostList(category)

        Log.d("getPostList() / ", "FormListIdea에서 메소드")
    }

    override fun onGetPostListSuccess(postList: PostListResponse) {
        binding.fragmentIdeaRv.adapter = FormListRAdapter(postList.result)
        Toast.makeText(activity, "폼 목록을 불러오는데 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onGetPostListFailure() {
        Toast.makeText(activity, "폼 목록을 불러오는데 실패했습니다", Toast.LENGTH_SHORT).show()
    }

}