package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.umc_hackathon.databinding.ActivityJoinBinding
import com.example.umc_hackathon.post.MainActivity

class JoinActivity : AppCompatActivity(), JoinView {

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.joinTitle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.joinSubmitBtn.setOnClickListener {
            join()
        }

        Log.d("JOIN/JWT_TO_SERVICE/", " ")
    }

    private fun getUser(): User {
        val id: String = binding.joinIdEt.text.toString()
        val nick: String = binding.joinNicknameEt.text.toString()
        val pwd: String = binding.joinPasswordEt.text.toString()

        return User(id, nick, pwd)
    }

    private fun join() {
        Log.d("JOIN()", "메소드")

        if(binding.joinIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        
        if(binding.joinNicknameEt.text.toString().isEmpty()) {
            Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.joinPasswordEt.text.toString() != binding.joinPasswordCheckEt.text.toString()) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setJoinView(this)
        authService.join(getUser())
    }

    private fun clearInputText() {
        binding.joinIdEt.setText("")
        binding.joinNicknameEt.setText("")
        binding.joinPasswordEt.setText("")
        binding.joinPasswordCheckEt.setText("")
    }

    // JoinView 상속
    override fun onJoinSuccess() {
        Toast.makeText(this, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
        clearInputText()
    }

    override fun onJoinFailure() {
        Toast.makeText(this, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
        clearInputText()
    }
}