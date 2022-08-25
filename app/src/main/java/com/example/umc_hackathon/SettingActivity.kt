package com.example.umc_hackathon

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.umc_hackathon.auth.MyPageActivity
import com.example.umc_hackathon.databinding.ActivitySettingBinding
import com.example.umc_hackathon.post.MainActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingCancelTv.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.settingLogoutCv.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setTitle("로그아웃 하시겠습니까?")

            fun toast() {
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()

                val authSpf = getSharedPreferences("auth", MODE_PRIVATE)
                val authEditor = authSpf.edit()
                authEditor.remove("jwt")
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
}