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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.databinding.AddItemDialogBinding
import com.example.umc_hackathon.post.FormListActivity

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

        // 설문 타입 스피너
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

        optionRAdapter.addItem(Option(""))

        // 이벤트 리스너
        binding.formCreateCancelTv.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }

        builderItem.dialogOptionLl.setOnClickListener {
            optionRAdapter.addItem(Option(""))

            Log.d("dialogOptionLl", "눌림")
        }

//        Log.d("스피너", questionSpinner.selectedItem.toString())
//        if(questionSpinner.selectedItem.toString() == "주관식") {
//            builderItem.dialogOptionLl.visibility = View.GONE
//        } else if (questionSpinner.selectedItem.toString() == "객관식") {
//            builderItem.dialogOptionLl.visibility = View.VISIBLE
//        }

        var questionEt = builderItem.dialogQuestionEt
        var questionSpinner = builderItem.dialogTypeSpinner

        binding.formCreatePlusIv.setOnClickListener {
            Log.d("항목 추가", " 선택")

            var question: String
            var spinner: String

            AlertDialog.Builder(this).run {
                setTitle("항목 추가")
                if (builderItem.root.parent != null) {
                    (builderItem.root.parent as ViewGroup).removeView(builderItem.root)
                    questionEt.setText("")
                }
                setView(builderItem.root)
                setPositiveButton("항목 저장", DialogInterface.OnClickListener { dialogInterface, i ->
                    question = questionEt.text.toString()
                    spinner = questionSpinner.selectedItem.toString()

                    Log.d("항목 저장(질문) : ", question)
                    Log.d("항목 저장(스피너) : ", spinner)

                    rAdapter.addItem(MyQuestion(question))
                })
                setNegativeButton("취소", null)
                show()
            }
        }
    }
}