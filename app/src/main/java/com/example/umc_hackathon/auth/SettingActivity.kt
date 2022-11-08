package com.example.umc_hackathon.auth

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_hackathon.databinding.DialogSettingBinding
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.umc_hackathon.databinding.ActivitySettingBinding
import com.example.umc_hackathon.post.MainActivity

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


        binding.settingQuitCv.setOnClickListener {
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

        binding.settingLogoutCv.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃 하시겠습니까?")

            fun toast() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()

                val authSpf = getSharedPreferences("auth", MODE_PRIVATE)
                val authEditor = authSpf.edit()
                authEditor.remove("accessToken")
                authEditor.commit()
                authEditor.remove("refreshToken")
                authEditor.commit()

                val nickNameSpf = getSharedPreferences("nickName", MODE_PRIVATE)
                val nickNameEditor = nickNameSpf.edit()
                nickNameEditor.remove("nickName")
                nickNameEditor.commit()

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

            var dialogListener = DialogInterface.OnClickListener { p0, p1 ->
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE -> toast()
                }
            }

            dialog.setPositiveButton("네", dialogListener)
            dialog.setNegativeButton("아니요", null)
            dialog.show()
        }
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    private fun quitUser() {
        val authService = AuthService()
        authService.setUserSettingView(this)
        authService.deleteUser(getAccessToken().toString(), getRefreshToken().toString())
    }

    override fun onUserDeleteSuccess() {
        TODO("Not yet implemented")
    }

    override fun onUserDeleteFailure() {
        TODO("Not yet implemented")
    }
}