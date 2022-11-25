package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

//        val keyHash = Utility.getKeyHash(this)
//        Log.d("Hash", keyHash)

        val joinFragment = JoinFragment()

        binding.loginGoSignupTv.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.auth_fv, joinFragment)
            transaction.commit()

            // 로그인 하러 가기 텍스트
            binding.loginGoLoginLl.visibility = View.VISIBLE

            // 회원가입 추가 권유 텍스트
            binding.loginJoinLl.visibility = View.INVISIBLE
            // 비밀번호 찾기 텍스트
            binding.findPasswordLl.visibility = View.INVISIBLE
        }

        binding.goFindPasswordTv.setOnClickListener {
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            intent.putExtra("postId", postId)
//            intent.putExtra("postTitle", postTitle)
//            startActivity(intent)
        }
    }

    private fun initTransactionEvent() {
        val loginFragment = LoginFragment()
        val joinFragment = JoinFragment()

        supportFragmentManager.beginTransaction().add(R.id.auth_fv, loginFragment).commit()

        binding.loginGoLoginTv.setOnClickListener {
            // 버튼색
//            binding.authLoginCategoryBtn.setBackgroundResource(R.drawable.category_selected)
//            binding.authJoinCategoryBtn.setBackgroundResource(R.drawable.category_unselected)

            // 글자색
//            binding.authLoginCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
//            binding.authJoinCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.unselected_color))
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.auth_fv, loginFragment)
            transaction.commit()

            // 로그인 하러 가기 텍스트
            binding.loginGoLoginLl.visibility = View.INVISIBLE

            // 회원가입 추가 권유 텍스트
            binding.loginJoinLl.visibility = View.VISIBLE
            // 비밀번호 찾기 텍스트
            binding.findPasswordLl.visibility = View.VISIBLE

        }

        binding.loginGoSignupTv.setOnClickListener {
            // 버튼색
//            binding.authLoginCategoryBtn.setBackgroundResource(R.drawable.category_unselected)
//            binding.authJoinCategoryBtn.setBackgroundResource(R.drawable.category_selected)

            // 글자색
//            binding.authLoginCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.unselected_color))
//            binding.authJoinCategoryBtn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.auth_fv, joinFragment)
            transaction.commit()

            // 로그인 하러 가기 텍스트
            binding.loginGoLoginLl.visibility = View.VISIBLE

            // 회원가입 추가 권유 텍스트
            binding.loginJoinLl.visibility = View.INVISIBLE
            // 비밀번호 찾기 텍스트
            binding.findPasswordLl.visibility = View.INVISIBLE
        }
    }
}