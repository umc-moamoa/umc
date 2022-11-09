package com.example.umc_hackathon.auth

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_hackathon.R
import kotlinx.android.synthetic.main.dialog_logout.*
import kotlinx.android.synthetic.main.dialog_setting.*

class CustomDialog(
    context: Context
) { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.

    private lateinit var onClickListener: OnDialogClickListener
    private val dialog = Dialog(context)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // 만들어놓은 dialog_profile.xml 뷰를 띄운다.
//        binding = DialogSettingBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        initViews()
//    }

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun quitInitViews(){
        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        dialog.setContentView(R.layout.dialog_setting)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.show()

        // OK Button 클릭에 대한 Callback 처리. 이 부분은 상황에 따라 자유롭게!
        dialog.leave_btn.setOnClickListener {
            onClickListener.onClicked(true)
            dialog.dismiss()
        }
        dialog.cancel_btn.setOnClickListener {
            onClickListener.onClicked(false)
            dialog.dismiss()
        }

    }

    fun logoutInitViews(){
        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
        dialog.setContentView(R.layout.dialog_logout)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.show()

        // OK Button 클릭에 대한 Callback 처리. 이 부분은 상황에 따라 자유롭게!
        dialog.logout_cancel_btn.setOnClickListener {
            onClickListener.onClicked(false)
            dialog.dismiss()
        }
        dialog.logout_btn.setOnClickListener {
            onClickListener.onClicked(true)
            dialog.dismiss()
        }

    }


    interface OnDialogClickListener
    {
        fun onClicked(flag: Boolean)
    }
}