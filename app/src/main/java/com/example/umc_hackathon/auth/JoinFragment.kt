package com.example.umc_hackathon.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.umc_hackathon.databinding.FragmentJoinBinding
import java.util.regex.Pattern

class JoinFragment : Fragment(), JoinView, JoinCheckView {

    private lateinit var binding: FragmentJoinBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentJoinBinding.inflate(layoutInflater)

        // joinCheck
        binding.joinIdDuplicateCheckCv.setOnClickListener {
            joinIdCheck()
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

    private fun getUser(): User {
        val id: String = binding.joinIdEt.text.toString()
        val nick: String = binding.joinNicknameEt.text.toString()
        val pwd: String = binding.joinPasswordEt.text.toString()

        return User(id, nick, pwd)
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

    private fun joinNickCheck() {
        val authService = AuthService()
        authService.setJoinCheckView(this)
        authService.joinCheck("", getUser().nick)
    }

    override fun onJoinIdCheckSuccess() {
        binding.joinIdDuplicateCheckTv.visibility = View.INVISIBLE
        binding.joinIdDuplicateCheckYesIv.visibility = View.VISIBLE
        binding.joinIdDuplicateCheckNoIv.visibility = View.INVISIBLE
        Toast.makeText(activity, "아이디 중복 확인에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onJoinIdCheckFailure() {
        binding.joinIdDuplicateCheckTv.visibility = View.INVISIBLE
        binding.joinIdDuplicateCheckYesIv.visibility = View.INVISIBLE
        binding.joinIdDuplicateCheckNoIv.visibility = View.VISIBLE
        Toast.makeText(activity, "아이디 중복 확인에 실패했습니다", Toast.LENGTH_SHORT).show()
        binding.joinSubmitBtn.isEnabled = false
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