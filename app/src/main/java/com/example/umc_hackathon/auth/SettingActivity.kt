package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(), UserSettingView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingCancelTv.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.settingQuitCv.setOnClickListener {
            quitUser()
        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun quitUser() {
        val authService = AuthService()
        authService.setUserSettingView(this)
        authService.deleteUser(getJwt().toString())
    }

    override fun onUserDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onUserDeleteFailure() {
        TODO("Not yet implemented")
    }
}