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
import com.example.umc_hackathon.databinding.FragmentJoinBinding
import com.example.umc_hackathon.databinding.FragmentLoginBinding
import com.example.umc_hackathon.post.MainActivity
import java.util.regex.Pattern

class JoinFragment : Fragment(), JoinView, JoinCheckView {

    private lateinit var binding: FragmentJoinBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentJoinBinding.inflate(layoutInflater)

        // joinCheck
        binding.joinIdDuplicateCheckCv.setOnClickListener {
            joinIdCheck()
        }

        //이메일 인증 전송
//        binding.joinIdDuplicateCheckEt.setOnClickListener {
//            joinIdCheck()
//            emailSend()
//        }

        //이메일 인증번호 체크
        binding.joinEmailCertificateCheckCv.setOnClickListener {
            emailCertificate()
        }

        binding.joinNicknameDuplicateCheckCv.setOnClickListener {
            joinNickCheck()
        }

        binding.joinSubmitBtn.setOnClickListener {
            join()
        }

        binding.joinPasswordEt.setOnClickListener {
            if(isRegularPW(binding.joinPasswordEt.text.toString()) == true) {
                binding.joinPasswordLimitTv.text = "올바른 비밀번호 입니다."
            }
            else {
                binding.joinPasswordLimitTv.text = "올바른 비밀번호가 아닙니다."
            }
        }

        binding.joinPasswordCheckEt.setOnClickListener{
            if(binding.joinPasswordEt.text.toString() == binding.joinPasswordCheckEt.text.toString()) {
                binding.joinPasswordCheckTv.text = "비밀번호가 일치합니다"
                binding.joinPasswordCheckTv.visibility = View.VISIBLE
            }
            else {
                binding.joinPasswordCheckTv.text = "비밀번호가 일치하지 않습니다"
                binding.joinPasswordCheckTv.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    private fun getUser(): UserSign {
        val id: String = binding.joinIdEt.text.toString()
        val nick: String = binding.joinNicknameEt.text.toString()
        val pwd: String = binding.joinPasswordEt.text.toString()

        return UserSign(id, nick, pwd)
    }

    private fun join() {
        Log.d("JOIN()", "메소드")

        if(binding.joinIdEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.joinNicknameEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(isRegularPW(binding.joinPasswordEt.text.toString()) == false) {
            Toast.makeText(activity, "올바른 비밀번호가 아닙니다", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.joinPasswordEt.text.toString() != binding.joinPasswordCheckEt.text.toString()) {
            Toast.makeText(activity, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
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
        Toast.makeText(activity, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
        clearInputText()
    }

    override fun onJoinFailure() {
        Toast.makeText(activity, "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show()
        clearInputText()
    }

    // joinCheck
    private fun joinIdCheck() {
        val authService = AuthService()
        authService.setJoinCheckView(this)
        authService.joinCheck(getUser().id, "")
    }

    private fun emailSend() {
        val authService = AuthService()
        authService.setJoinCheckView(this)
        authService.emailSend(getUser().id)
    }

    private fun emailCertificate() {
        val authService = AuthService()
        authService.setJoinCheckView(this)

        val code: String = binding.joinEmailCertificateCheckEt.text.toString()
        authService.emailCertificate(code)
    }

    private fun joinNickCheck() {
        val nick: String = binding.joinNicknameEt.text.toString()
        val authService = AuthService()
        authService.setJoinCheckView(this)
        authService.joinCheck("", nick)
    }

    override fun onJoinIdCheckSuccess() {
        binding.joinIdDuplicateCheckTv.visibility = View.INVISIBLE
        binding.joinIdDuplicateCheckYesIv.visibility = View.VISIBLE
        binding.joinIdDuplicateCheckNoIv.visibility = View.INVISIBLE
        Toast.makeText(activity, "아이디 중복 확인에 성공했습니다", Toast.LENGTH_SHORT).show()
        //이메일 발송하도록 수정
    }

    override fun onJoinIdCheckFailure() {
        binding.joinIdDuplicateCheckTv.visibility = View.INVISIBLE
        binding.joinIdDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.joinIdDuplicateCheckNoIv.visibility = View.VISIBLE
        Toast.makeText(activity, "아이디 중복 확인에 실패했습니다", Toast.LENGTH_SHORT).show()
        binding.joinSubmitBtn.isEnabled = false
    }

    override fun onEmailSendSuccess() {
        Toast.makeText(activity, "이메일 전송 완료", Toast.LENGTH_SHORT).show()
    }

    override fun onEmailSendFailure() {
        Toast.makeText(activity, "이메일 전송에 실패했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onEmailCertificateSuccess() {
        Toast.makeText(activity, "이메일 인증에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onEmailCertificateFailure(code: Int) {
        if (code == 2063) {
            Toast.makeText(activity, "인증번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(activity, "이메일 인증에 실패했습니다", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onJoinNickCheckSuccess() {
        binding.joinNicknameDuplicateCheckTv.visibility = View.INVISIBLE
        binding.joinNicknameDuplicateCheckYesIv.visibility = View.VISIBLE
        binding.joinNicknameDuplicateCheckNoIv.visibility = View.INVISIBLE
        Toast.makeText(activity, "닉네임 중복 확인에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onJoinNickCheckFailure() {
        binding.joinNicknameDuplicateCheckTv.visibility = View.INVISIBLE
        binding.joinNicknameDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.joinNicknameDuplicateCheckNoIv.visibility = View.VISIBLE
        Toast.makeText(activity, "닉네임 중복 확인에 실패했습니다", Toast.LENGTH_SHORT).show()
        binding.joinSubmitBtn.isEnabled = false
    }

    private fun isRegularPW(password: String): Boolean {
        val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?^&.])[A-Za-z[0-9]$@$!%*#?^&.]{6,15}$"
        val pattern = Pattern.compile(pwPattern)
        val matcher = pattern.matcher(pwPattern)
        Log.d("Match", matcher.find().toString())

        return (Pattern.matches(pwPattern, password))
    }

}