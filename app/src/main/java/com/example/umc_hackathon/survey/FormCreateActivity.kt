package com.example.umc_hackathon.survey

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.databinding.AddItemDialogBinding
import com.example.umc_hackathon.post.FormListActivity
import kotlinx.android.synthetic.main.dialog_option_item.*
import org.w3c.dom.Text

class FormCreateActivity : AppCompatActivity() {

    val builderItem by lazy {AddItemDialogBinding.inflate(layoutInflater)}

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFormCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

         // 카테고리 스피너
        val categoryList = listOf("마케팅", "사회현상", "브랜드", "아이디어")
        binding.formCreateSelectCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                println(categoryList[pos] + "입니다")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }
        }

        // 설문 타입 스피너에 따라 옵션 visibility 선택
        val typeList = listOf("객관식", "주관식")
        builderItem.dialogTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                if(pos == 0) {
                    builderItem.dialogOptionLl.visibility = View.VISIBLE
                    builderItem.dialogItemOptionRv.visibility = View.VISIBLE
                } else if (pos == 1) {
                    builderItem.dialogOptionLl.visibility = View.GONE
                    builderItem.dialogItemOptionRv.visibility = View.GONE
                }

                println(typeList[pos] + "입니다")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }
        }

        // 설문 작성 아이템 리사이클러뷰
        var questionList = arrayListOf<MyQuestion>()
        val rAdapter = FormCreateRAdapter(questionList)

        binding.formCreateListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formCreateListRv.setHasFixedSize(true)
        binding.formCreateListRv.adapter = FormCreateRAdapter(questionList)

        // 다이얼로그 옵션 리사이클러뷰
        var optionList = arrayListOf<Option>()
        val optionRAdapter = OptionRAdapter(optionList)

        builderItem.dialogItemOptionRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        builderItem.dialogItemOptionRv.setHasFixedSize(true)
        builderItem.dialogItemOptionRv.adapter = OptionRAdapter(optionList)

        // 이벤트 리스너
        binding.formCreateCancelTv.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 옵션 추가
        builderItem.dialogOptionAddIv.setOnClickListener {
            Log.d("(추가) 옵션 개수", optionRAdapter.itemCount.toString())
            Log.d("옵션 추가 내용", builderItem.dialogOptionInputEt.text.toString())

            optionRAdapter.addItem(Option(builderItem.dialogOptionInputEt.text.toString()))

//            for(x in 0 until optionList.size) {
//                Log.d("옵션", "항목 $x : ${optionList[x].question}")
//            }

            builderItem.dialogOptionInputEt.setText("") // 초기화 시키기
        }


        var questionEt = builderItem.dialogQuestionEt
        var questionSpinner = builderItem.dialogTypeSpinner
        var question: String
        var spinner: String

        binding.formCreatePlusIv.setOnClickListener {
            Log.d("질문 추가", " 선택")

            question= questionEt.text.toString()
            spinner = questionSpinner.selectedItem.toString()

            AlertDialog.Builder(this).run {
                setTitle("질문 추가")
                if (builderItem.root.parent != null) {
                    (builderItem.root.parent as ViewGroup).removeView(builderItem.root)
                    questionEt.setText("")
                }
                setView(builderItem.root)
                setPositiveButton("질문 저장", DialogInterface.OnClickListener { dialogInterface, i ->
                    question = questionEt.text.toString()
                    spinner = questionSpinner.selectedItem.toString()

                    Log.d("질문 저장(질문) : ", question)
                    Log.d("질문 저장(스피너) : ", spinner)

                    rAdapter.addItem(MyQuestion(question, optionList))
                })
                setNegativeButton("취소", null)
                show()
            }
        }
    }
}