package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.umc_hackathon.*
import com.example.umc_hackathon.databinding.ActivityFormListBinding
import com.example.umc_hackathon.survey.FormCreateActivity

class FormListActivity : AppCompatActivity(){

    private lateinit var binding : ActivityFormListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 첫 화면은 마케팅 탭으로 초기화
        initTransactionEvent()

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

    // 카테고리 변환
    private fun initTransactionEvent() {
        val marketingFragment = FormListMarketing()
        val socialFragment = FormListSocial()
        val brandFragment = FormListBrand()
        val ideaFragment = FormListIdea()

        // FragmentManager 호출
        supportFragmentManager.beginTransaction().add(R.id.form_list_fv, marketingFragment).commit()

        // Transaction 작업
        binding.formListMarketingBtn.setOnClickListener {
            // 버튼색
            binding.formListMarketingBtn.setBackgroundResource(R.drawable.category_selected)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
            binding.formListBrandBtn.setBackgroundResource(R.color.transparent)
            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)

            // 글자색
            binding.formListMarketingBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            binding.formListSocialPhenomenonBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListBrandBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListIdeaBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, marketingFragment)
            transaction.commit()
        }

        binding.formListSocialPhenomenonBtn.setOnClickListener {
            // 버튼색
            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.drawable.category_selected)
            binding.formListBrandBtn.setBackgroundResource(R.color.transparent)
            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)
            
            // 글자색
            binding.formListMarketingBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListSocialPhenomenonBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            binding.formListBrandBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListIdeaBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, socialFragment)
            transaction.commit()
        }

        binding.formListBrandBtn.setOnClickListener {
            // 버튼색
            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
            binding.formListBrandBtn.setBackgroundResource(R.drawable.category_selected)
            binding.formListIdeaBtn.setBackgroundResource(R.color.transparent)

            // 글자색
            binding.formListMarketingBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListSocialPhenomenonBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListBrandBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            binding.formListIdeaBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, brandFragment)
            transaction.commit()
        }

        binding.formListIdeaBtn.setOnClickListener {
            // 버튼색
            binding.formListMarketingBtn.setBackgroundResource(R.color.transparent)
            binding.formListSocialPhenomenonBtn.setBackgroundResource(R.color.transparent)
            binding.formListBrandBtn.setBackgroundResource( R.color.transparent)
            binding.formListIdeaBtn.setBackgroundResource(R.drawable.category_selected)

            // 글자색
            binding.formListMarketingBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListSocialPhenomenonBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListBrandBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            binding.formListIdeaBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.form_list_fv, ideaFragment)
            transaction.commit()
        }
    }

}