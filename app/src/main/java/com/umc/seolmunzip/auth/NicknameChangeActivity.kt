package com.umc.seolmunzip.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.umc.seolmunzip.databinding.ActivityNicknameChangeBinding
import com.umc.seolmunzip.my.MyPageActivity

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
            else
                clearInputText()
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

        flag = true
    }

    override fun onNickCheckFailure() {
        binding.nicknameChangeDuplicateCheckTv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.nicknameChangeDuplicateCheckNoIv.visibility = View.VISIBLE

        flag = false
    }

    override fun onNickChangeSuccess(resp: NickChangeResponse) {
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