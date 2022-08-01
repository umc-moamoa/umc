package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.*
import com.example.umc_hackathon.databinding.ActivityFormListBinding

class FormListActivity : AppCompatActivity(), PostView {

    val TAG: String = "<FormListActivity>"

    private lateinit var binding : ActivityFormListBinding
    //private lateinit var postList : List<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTransactionEvent()

//        binding.formListListItemRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.formListListItemRv.setHasFixedSize(true)
//        getAllPosts()
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

//        binding.formListMarketingBtn.setOnClickListener {
//            binding.formListMarketingBtn.setBackgroundResource(R.drawable.btn_style)
//            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
//            binding.formListBrandBtn.setBackgroundResource(R.color.transparent)
//            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)
//        }
//
//        binding.formListSocialPhenomenonBtn.setOnClickListener {
//            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
//            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.drawable.btn_style)
//            binding.formListBrandBtn.setBackgroundResource(R.color.transparent)
//            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)
//        }
//
//        binding.formListBrandBtn.setOnClickListener {
//            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
//            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
//            binding.formListBrandBtn.setBackgroundResource(R.drawable.btn_style)
//            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)
//        }
//
//        binding.formListIdeaBtn.setOnClickListener {
//            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
//            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
//            binding.formListBrandBtn.setBackgroundResource( R.color.transparent)
//            binding.formListIdeaBtn.setBackgroundResource(R.drawable.btn_style)
//        }
    }

    fun initTransactionEvent() {
        val marketingFragment = FormListMarketing()
        val socialFragment = FormListSocial()
        val brandFragment = FormListBrand()
        val ideaFragment = FormListIdea()

        // FragmentManager 호출
        supportFragmentManager.beginTransaction().add(R.id.form_list_fv, marketingFragment).commit()

        // Transaction 작업
        binding.formListMarketingBtn.setOnClickListener {
            binding.formListMarketingBtn.setBackgroundResource(R.drawable.btn_style)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
            binding.formListBrandBtn.setBackgroundResource(R.color.transparent)
            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, marketingFragment)
            transaction.commit()
        }

        binding.formListSocialPhenomenonBtn.setOnClickListener {
            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.drawable.btn_style)
            binding.formListBrandBtn.setBackgroundResource(R.color.transparent)
            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, socialFragment)
            transaction.commit()
        }

        binding.formListBrandBtn.setOnClickListener {
            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
            binding.formListBrandBtn.setBackgroundResource(R.drawable.btn_style)
            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, brandFragment)
            transaction.commit()
        }

        binding.formListIdeaBtn.setOnClickListener {
            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
            binding.formListBrandBtn.setBackgroundResource( R.color.transparent)
            binding.formListIdeaBtn.setBackgroundResource(R.drawable.btn_style)

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, ideaFragment)
            transaction.commit()
        }


    }

    private fun getAllPosts() {
        val postService = PostService()
        postService.setPostView(this)
        postService.getAllPost(1) //카테고리는 추후 받아오는 걸로 수정
    }

    override fun onGetAllPostSuccess(postList: PostListResponse) {
//        this.postList = postList
//        binding.formListListItemRv.adapter = FormListRAdapter(postList.result)
    }

    override fun onGetPostDetail(post: Post) {
        TODO("Not yet implemented")
    }

}