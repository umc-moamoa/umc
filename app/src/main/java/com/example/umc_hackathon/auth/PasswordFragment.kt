package com.example.umc_hackathon.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.umc_hackathon.R
import com.example.umc_hackathon.auth.dto.User
import com.example.umc_hackathon.auth.view.EmailView
import com.example.umc_hackathon.auth.view.JoinCheckView
import com.example.umc_hackathon.auth.view.PasswordView
import com.example.umc_hackathon.databinding.FragmentPasswordBinding
import com.example.umc_hackathon.post.list.FormListActivity
import java.util.regex.Pattern

class PasswordFragment : Fragment(), EmailView, PasswordView {

    private lateinit var binding: FragmentPasswordBinding
    private lateinit var code: String
    private lateinit var email: String
    private lateinit var pwd: String
    private var flag: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(layoutInflater)

        binding.changePasswordEt.setOnClickListener {
            if(isRegularPW(binding.changePasswordEt.text.toString())) {
                binding.changePasswordLimitTv.text = "올바른 비밀번호 입니다."
            }
            else {
                binding.changePasswordLimitTv.text = "올바른 비밀번호가 아닙니다."
            }
        }

        //이메일 인증 전송
        binding.emailSendCv.setOnClickListener {
            email = binding.emailEt.text.toString()
            emailSend(email)
        }

        //이메일 인증번호 체크
        binding.emailCertificateCheckCv.setOnClickListener {
            emailCertificate()
        }

        //비밀번호 일치 체크
        binding.changePasswordCheckEt.setOnClickListener{
            if(binding.changePasswordEt.text.toString() == binding.changePasswordCheckEt.text.toString()) {
                binding.changePasswordCheckTv.text = "비밀번호가 일치합니다"
                binding.changePasswordCheckTv.visibility = View.VISIBLE
                pwd = binding.changePasswordCheckEt.text.toString()
                flag = true
            }
            else {
                binding.changePasswordCheckTv.text = "비밀번호가 일치하지 않습니다"
                binding.changePasswordCheckTv.visibility = View.VISIBLE
            }
        }

        //비밀번호 변경 - 이멜 인증여부, 비번 일치체크 여부 확인 후
        binding.changePasswordSubmitBtn.setOnClickListener{
            val user = User(email, pwd)
            changePassword(user)
        }

        return binding.root
    }

    private fun clearInputText() {
        binding.emailEt.setText("")
        binding.changePasswordEt.setText("")
        binding.changePasswordCheckEt.setText("")
    }


    private fun emailSend(email : String) {
        val authService = AuthService()
        authService.setEmailView(this)
        authService.emailSend(email)
    }

    private fun emailCertificate() {
        val authService = AuthService()
        authService.setEmailView(this)

        val etCode: String = binding.emailCertificateEt.text.toString()
        authService.emailCertificate(code, etCode)
    }

    private fun changePassword(user: User) {
        val authService = AuthService()
        authService.setEmailView(this)
        authService.changePassword(user)
    }

    override fun onEmailSendSuccess(result: String) {
        Toast.makeText(activity, "이메일 전송 완료", Toast.LENGTH_SHORT).show()
        code = result
    }

    override fun onEmailSendFailure() {
        Toast.makeText(activity, "이메일 전송에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onEmailCertificateSuccess() {
        Toast.makeText(activity, "이메일 인증에 성공했습니다", Toast.LENGTH_SHORT).show()
        flag = true
    }

    override fun onEmailCertificateFailure(code: Int) {
        if (code == 2063) {
            Toast.makeText(activity, "인증번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(activity, "이메일 인증에 실패했습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isRegularPW(password: String): Boolean {
        val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?^&.])[A-Za-z[0-9]$@$!%*#?^&.]{6,15}$"
        val pattern = Pattern.compile(pwPattern)
        val matcher = pattern.matcher(pwPattern)
        Log.d("Match", matcher.find().toString())

        return (Pattern.matches(pwPattern, password))
    }

    override fun changePasswordSuccess() {
        Toast.makeText(activity, "비밀번호 변경에 성공했습니다", Toast.LENGTH_SHORT).show()
        clearInputText()

        val intent = Intent(activity, AuthActivity::class.java)
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)

    }

    override fun changePasswordFailure() {
        Toast.makeText(activity, "비밀번호 변경에 실패했습니다", Toast.LENGTH_SHORT).show()
        clearInputText()
    }
}