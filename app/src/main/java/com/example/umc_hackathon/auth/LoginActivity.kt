package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.databinding.ActivityJoinBinding
import com.example.umc_hackathon.databinding.ActivityLoginBinding
import com.example.umc_hackathon.post.MainActivity

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginJoinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.loginTitle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.loginSubmitBtn.setOnClickListener {
            login()
        }

        Log.d("LOGIN/JWT_TO_SERVICE/", " ")
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun login() {
        Log.d("LOGIN()", "메소드")

        if(binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        
        if(binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val id = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)
        authService.login(User(id, "", password))
    }

    // LoginView 상속
    override fun onLoginSuccess(code: Int, result: LoginResult) {
        when(code) {
            1000 -> {
                val intent = Intent(this, MyPageActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()

                saveJwt(result.jwt)
                onStart()
            }
        }
    }

    override fun onLoginFailure(code: Int) {
        when(code) {
            3014 -> {
                Toast.makeText(this, "없는 아이디거나 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
            4000 -> {
                Toast.makeText(this, "데이터베이스 연결에 실패하였습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
            4011 -> {
                Toast.makeText(this, "비밀번호 암호화에 실패했습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
            else -> {
                Toast.makeText(this, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
        }
    }
}