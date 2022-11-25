package com.example.umc_hackathon.post.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.FragmentFormListSocialBinding
import com.example.umc_hackathon.post.PostService

class FormListSocial : Fragment(), PostListView {

    private val categoryId: Long = 2
    private lateinit var binding: FragmentFormListSocialBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFormListSocialBinding.inflate(layoutInflater)

        binding.fragmentSocialRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentSocialRv.setHasFixedSize(true)
        getPostList(categoryId)

        return binding.root
    }

    private fun getPostList(category: Long) {
        val postService = PostService()
        postService.setPostListView(this)
        postService.getPostList(category)

        Log.d("getPostList() / ", "FormListSocial에서 메소드")
    }

    override fun onGetPostListSuccess(postList: PostListResponse) {
        binding.fragmentSocialRv.adapter = FormListRAdapter(postList.result)
        Log.d("FormListSocial / ", "사회현상 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetPostListFailure() {
        Log.d("FormListSocial / ", "사회현상 폼 목록을 불러오는데 실패했습니다")
    }

}