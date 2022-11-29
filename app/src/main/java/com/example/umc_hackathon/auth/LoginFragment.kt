package com.example.umc_hackathon.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_hackathon.auth.dto.LoginResponse
import com.example.umc_hackathon.auth.dto.User
import com.example.umc_hackathon.auth.view.LoginView
import com.example.umc_hackathon.databinding.FragmentLoginBinding
import com.example.umc_hackathon.my.MyPageActivity
import com.example.umc_hackathon.post.StringResultResponse
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginFragment : Fragment(), LoginView {


    private final var TAG = "LoginFragment"
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.loginSubmitBtn.setOnClickListener {
            login()
        }

        binding.loginKakaoBtn.setOnClickListener{
            kakao_login()
        }

        return binding.root
    }

    private fun getAccessToken(): String? {
        val spf = requireActivity().getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun saveAccessToken(accessToken: String) {
        val spf = requireActivity().getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("accessToken", accessToken)
        editor.apply()
    }

    private fun saveRefreshToken(refreshToken: String) {
        val spf = requireActivity().getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf.edit()

        editor.putString("refreshToken", refreshToken)
        editor.apply()
    }

    private fun kakao_login() {

        val authService = AuthService()
        authService.setLoginView(this)

        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.d("카카오계정으로 로그인 실패", error.toString())
                Toast.makeText(activity, "카카오 계정 연결에 실패했습니다.", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                Log.d("카카오계정으로 로그인 성공", token.accessToken)
                authService.kakaoLogin(token.accessToken)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                if (error != null) {
                    Log.e("카카오톡으로 로그인 실패", error.toString())

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                } else if (token != null) {
                    Log.d("카카오톡으로 로그인 성공", token.accessToken)
                    authService.kakaoLogin(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun login() {
        Log.d(TAG, "login() 실행")

       if(binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        if(binding.loginPasswordEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }

        val id = binding.loginIdEt.text.toString()
        val pwd = binding.loginPasswordEt.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)
        authService.login(User(id, pwd))
    }

    // LoginView 상속
    override fun onLoginSuccess(code: Int, result: StringResultResponse) {
        when(code) {
            1000 -> {
                val intent = Intent(activity, MyPageActivity::class.java)
//                intent.addFlags (Intent.FLAG_ACTIITY_NO_ANIMATION)
                startActivity(intent)
                requireActivity().finish()

                saveAccessToken(result.result)

                Log.d("로그인: 액세스 토근", getAccessToken().toString())

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

    override fun onKakaoLoginSuccess(code: Int, result: LoginResponse) {
        when(code) {
            1000 -> {
                val intent = Intent(activity, MyPageActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

                saveAccessToken(result.result.accessToken)
                Log.d("로그인: 액세스 토근", getAccessToken().toString())

                onStart()
            }
        }
    }


}