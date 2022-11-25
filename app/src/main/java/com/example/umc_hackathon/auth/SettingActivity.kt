package com.example.umc_hackathon.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_hackathon.databinding.DialogSettingBinding
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
            dialog.quitInitViews()
            dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener {
                override fun onClicked(flag: Boolean) {
                    if (flag) {
                        quitUser()
                    }
                }
            })
          }

        binding.settingLogoutCv.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.logoutInitViews()
            dialog.setOnClickListener(object : CustomDialog.OnDialogClickListener{
                override fun onClicked(flag: Boolean) {
                    if (flag) {
                        logoutUser()
                    }
                }
            })
//            var dialog = AlertDialog.Builder(this)
//            dialog.setTitle("로그아웃 하시겠습니까?")
//
//            fun toast() {
//                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
//
//                val authSpf = getSharedPreferences("auth", MODE_PRIVATE)
//                val authEditor = authSpf.edit()
//                authEditor.remove("jwt")
//                authEditor.commit()
//
//                val nickNameSpf = getSharedPreferences("nickName", MODE_PRIVATE)
//                val nickNameEditor = nickNameSpf.edit()
//                nickNameEditor.remove("nickName")
//                nickNameEditor.commit()
//
//                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                startActivity(intent)
//            }
//
//            var dialogListener = DialogInterface.OnClickListener { p0, p1 ->
//                when (p1) {
//                    DialogInterface.BUTTON_POSITIVE -> toast()
//                }
//            }
//
//            dialog.setPositiveButton("네", dialogListener)
//            dialog.setNegativeButton("아니요", null)
//            dialog.show()
        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun quitUser() {
        val authService = AuthService()
        authService.setUserSettingView(this)
        authService.deleteUser(getAccessToken().toString(), getRefreshToken().toString())
    }

    private fun logoutUser() {
        val authSpf = getSharedPreferences("auth", MODE_PRIVATE)
        val authEditor = authSpf.edit()
        authEditor.remove("accessToken")
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

    override fun onUserDeleteSuccess() {
        Log.d("userDelete: ", "quit-success")
        logoutUser()
    }

    override fun onUserDeleteFailure() {
        Log.d("userDelete: ", "quit-fail")
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