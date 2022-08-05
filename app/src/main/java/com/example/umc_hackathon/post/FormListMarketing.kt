package com.example.umc_hackathon.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.FragmentFormListMarketingBinding
import kotlinx.android.synthetic.main.fragment_form_list_marketing.*

class FormListMarketing : Fragment(), PostView {

    private lateinit var postList : List<Post>
    private var linearLayoutManager: RecyclerView.LayoutManager? = null
    private var formListAdapter: RecyclerView.Adapter<FormListRAdapter.MyViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFormListMarketingBinding.inflate(layoutInflater)
        val view = inflater!!.inflate(R.layout.fragment_form_list_marketing, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_marketing_rv)

        linearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        getAllPosts()

        return view
    }

//    override fun onStart() {
//        super.onStart()
//        getAllPosts()
//    }

    private fun initRecyclerView(result: PostListResponse) {
        formListAdapter = FormListRAdapter(postList)
        fragment_marketing_rv.adapter = formListAdapter
    }

    private fun getAllPosts() {
        val postService = PostService()
        postService.setPostView(this)
        postService.getAllPost(1) //카테고리는 추후 받아오는 걸로 수정
    }

    override fun onGetAllPostSuccess(result: PostListResponse) {
        this.postList = postList
        initRecyclerView(result)
    }

    override fun onGetPostDetail(post: Post) {
        Log.d("ON_GET_POST_DETAIL/", "onGetPostDetail")
    }

}