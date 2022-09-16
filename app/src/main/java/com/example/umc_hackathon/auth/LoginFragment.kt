package com.example.umc_hackathon.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.FragmentFormListBrandBinding
import com.example.umc_hackathon.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), LoginView {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.loginSubmitBtn.setOnClickListener {
            login()
        }

        return binding.root
    }

    private fun getJwt(): String? {
        val spf = activity!!.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun saveJwt(jwt: String) {
        val spf = activity!!.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("jwt", jwt)
        editor.apply()
    }

    private fun login() {
        Log.d("LOGIN()", "메소드")

        if(binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val id = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)
        authService.login(User(id, "", password))
    }

    // LoginView 상속
    override fun onLoginSuccess(code: Int, result: LoginResult) {
        when(code) {
            1000 -> {
                val intent = Intent(activity, MyPageActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                activity!!.finish()

                saveJwt(result.jwt)
                onStart()
            }
        }
    }

    override fun onLoginFailure(code: Int) {
        when(code) {
            3014 -> {
                Toast.makeText(activity, "없는 아이디거나 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
            4000 -> {
                Toast.makeText(activity, "데이터베이스 연결에 실패하였습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
            4011 -> {
                Toast.makeText(activity, "비밀번호 암호화에 실패했습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
            else -> {
                Toast.makeText(activity, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
                onStart()
            }
        }
    }

}