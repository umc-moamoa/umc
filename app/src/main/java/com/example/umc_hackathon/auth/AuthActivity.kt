package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityAuthBinding
import com.example.umc_hackathon.post.MainActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.authTitleIv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        initTransactionEvent()
    }

    private fun initTransactionEvent() {
        val loginFragment = LoginFragment()
        val joinFragment = JoinFragment()

        supportFragmentManager.beginTransaction().add(R.id.auth_fv, loginFragment).commit()

        binding.authLoginCategoryBtn.setOnClickListener {
            // 버튼색
            binding.authLoginCategoryBtn.setBackgroundResource(R.drawable.category_selected)
            binding.authJoinCategoryBtn.setBackgroundResource(R.drawable.category_unselected)

            // 글자색
            binding.authLoginCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            binding.authJoinCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.unselected_color))

            // 회원가입 추가 권유 텍스트
            binding.loginJoinLl.visibility = View.VISIBLE

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.auth_fv, loginFragment)
            transaction.commit()
        }

        binding.authJoinCategoryBtn.setOnClickListener {
            // 버튼색
            binding.authLoginCategoryBtn.setBackgroundResource(R.drawable.category_unselected)
            binding.authJoinCategoryBtn.setBackgroundResource(R.drawable.category_selected)

            // 글자색
            binding.authLoginCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.unselected_color))
            binding.authJoinCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))

            // 회원가입 추가 권유 텍스트
            binding.loginJoinLl.visibility = View.INVISIBLE

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.auth_fv, joinFragment)
            transaction.commit()
        }
    }
}