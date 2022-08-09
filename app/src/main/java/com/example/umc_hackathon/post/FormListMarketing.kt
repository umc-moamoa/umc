package com.example.umc_hackathon.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.FragmentFormListMarketingBinding
import kotlinx.android.synthetic.main.fragment_form_list_marketing.*

class FormListMarketing : Fragment(), PostListView {

    private val categoryId: Long = 1
    private lateinit var binding: FragmentFormListMarketingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFormListMarketingBinding.inflate(layoutInflater)

        binding.fragmentMarketingRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentMarketingRv.setHasFixedSize(true)
        getPostList(categoryId)

        return binding.root
    }

    private fun getPostList(category: Long) {
        val postService = PostService()
        postService.setPostListView(this)
        postService.getPostList(category)
        
        Log.d("getPostList() / ", "FormListMarketing에서 메소드")
    }

    override fun onGetPostListSuccess(postList: PostListResponse) {
        binding.fragmentMarketingRv.adapter = FormListRAdapter(postList.result.posts)
        Toast.makeText(activity, "폼 목록을 불러오는데 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onGetPostListFailure() {
        Toast.makeText(activity, "폼 목록을 불러오는데 실패했습니다", Toast.LENGTH_SHORT).show()
    }


}