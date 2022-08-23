package com.example.umc_hackathon.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.FragmentFormListBrandBinding

class FormListBrand : Fragment(), PostListView {

    private val categoryId: Long = 3
    private lateinit var binding: FragmentFormListBrandBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFormListBrandBinding.inflate(layoutInflater)

        binding.fragmentBrandRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentBrandRv.setHasFixedSize(true)
        getPostList(categoryId)

        return binding.root
    }

    private fun getPostList(category: Long) {
        val postService = PostService()
        postService.setPostListView(this)
        postService.getPostList(category)

        Log.d("getPostList() / ", "FormListBrand에서 메소드")
    }

    override fun onGetPostListSuccess(postList: PostListResponse) {
        binding.fragmentBrandRv.adapter = FormListRAdapter(postList.result)
        Log.d("FormListBrand / ", "브랜드 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetPostListFailure() {
        Log.d("FormListBrand / ", "브랜드 폼 목록을 불러오는데 실패했습니다")
    }

}