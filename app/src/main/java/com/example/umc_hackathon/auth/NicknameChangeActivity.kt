package com.example.umc_hackathon.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityNicknameChangeBinding

class NicknameChangeActivity : AppCompatActivity(), NickCheckView {

    private lateinit var binding: ActivityNicknameChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NickCheck
        binding.nicknameChangeDuplicateCheckCv.setOnClickListener {
            nickCheck()
        }
    }

    private fun nickCheck() {
        val nick: String = binding.nicknameChangeEt.text.toString()
        val authService = AuthService()
        authService.setNickCheckView(this)
        authService.nickCheck(nick)
    }

    override fun onNickCheckSuccess() {
        binding.nicknameChangeDuplicateCheckTv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckYesIv.visibility = View.VISIBLE
        binding.nicknameChangeDuplicateCheckNoIv.visibility = View.INVISIBLE
        Toast.makeText(this, "닉네임 중복 확인에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onNickCheckFailure() {
        binding.nicknameChangeDuplicateCheckTv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckNoIv.visibility = View.VISIBLE
        Toast.makeText(this, "닉네임 중복 확인에 실패했습니다", Toast.LENGTH_SHORT).show()
    }
}