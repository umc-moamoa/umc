package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityNicknameChangeBinding
import com.example.umc_hackathon.my.MyPageActivity

class NicknameChangeActivity : AppCompatActivity(), NickCheckView, NickChangeView {

    private lateinit var binding: ActivityNicknameChangeBinding
    var flag : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NickCheck
        binding.nicknameChangeDuplicateCheckCv.setOnClickListener {
            nickCheck()
        }

        // NickChange
        binding.nicknameChangeBtn.setOnClickListener {
            if(flag == true)
                nickChange()
            else {
                Toast.makeText(this, "닉네임 변경에 실패했습니다", Toast.LENGTH_SHORT).show()
                clearInputText()
            }
        }

        binding.nicknameChangeCancelBtn.setOnClickListener {
            finish()
        }
    }

    private fun nickCheck() {
        val nick: String = binding.nicknameChangeEt.text.toString()
        val authService = AuthService()
        authService.setNickCheckView(this)
        authService.nickCheck(nick)
    }

    private fun getNickChange(): NickChangeRequest {
        val newNick: String = binding.nicknameChangeEt.text.toString()

        return NickChangeRequest(newNick)
    }

    private fun nickChange() {
        Log.d("NICKCHANGE()", "메소드")

        if(binding.nicknameChangeEt.text.toString().isEmpty()) {
            Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val authService = AuthService()
        authService.setNickChangeView(this)
        authService.nickChange(getNickChange() ,getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun clearInputText() {
        binding.nicknameChangeEt.setText("")
        binding.nicknameChangeDuplicateCheckTv.visibility = View.VISIBLE
        binding.nicknameChangeDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckNoIv.visibility = View.INVISIBLE
    }

    override fun onNickCheckSuccess() {
        binding.nicknameChangeDuplicateCheckTv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckYesIv.visibility = View.VISIBLE
        binding.nicknameChangeDuplicateCheckNoIv.visibility = View.INVISIBLE
        Toast.makeText(this, "닉네임 중복 확인에 성공했습니다", Toast.LENGTH_SHORT).show()

        flag = true
    }

    override fun onNickCheckFailure() {
        binding.nicknameChangeDuplicateCheckTv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckNoIv.visibility = View.VISIBLE
        Toast.makeText(this, "닉네임 중복 확인에 실패했습니다", Toast.LENGTH_SHORT).show()

        flag = false
    }

    override fun onNickChangeSuccess(resp: NickChangeResponse) {
        Toast.makeText(this, "닉네임 변경에 성공했습니다", Toast.LENGTH_SHORT).show()
        val intent = Intent(this,  MyPageActivity::class.java)
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    override fun onNickChangeFailure() {
        Toast.makeText(this, "닉네임 변경에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    private fun getAccessToken(): String? {
        val spf = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }
}