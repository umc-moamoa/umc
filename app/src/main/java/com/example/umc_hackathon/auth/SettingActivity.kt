package com.example.umc_hackathon.auth

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.umc_hackathon.databinding.ActivitySettingBinding
import com.example.umc_hackathon.databinding.DialogSettingBinding

class SettingActivity : AppCompatActivity(), UserSettingView {

    private lateinit var dialogBinding: DialogSettingBinding

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

//        binding.settingQuitCv.setOnClickListener {
        binding.settingAlarmCv.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.initViews()
            dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                override fun onClicked(flag: Boolean) {
                    if (flag) {
                        quitUser()
                    }
                }
            })
//            val builder = AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog)
//            builder
//                .setMessage("탈퇴 후엔 보유하고 있던 포인트, 이력 등은 모두 소멸되며 복구는 불가능합니다. 정말 떠나시겠습니까?")
//                .setPositiveButton("나중에",
//                    DialogInterface.OnClickListener { dialog, id ->
//                    })
//                .setNegativeButton("떠나기",
//                    DialogInterface.OnClickListener { dialog, id ->
//                        quitUser()
//                    })
//                .setCancelable(false)
//            // 다이얼로그를 띄워주기
//            builder.show()
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