package com.example.umc_hackathon.survey

import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.post.FormListActivity
import okhttp3.internal.notify

class FormCreateActivity : AppCompatActivity(){

    private lateinit var binding: ActivityFormCreateBinding

    var myQuestionList = arrayListOf<MyQuestion>()
    var formCreateRAdapter = FormCreateRAdapter(myQuestionList)

    var optionList = arrayListOf<Option>()
    var optionRAdapter = OptionRAdapter(optionList)

    var categoryId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 설문 작성 아이템 리사이클러뷰
        binding.formCreateRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formCreateRv.setHasFixedSize(true)
        binding.formCreateRv.adapter = formCreateRAdapter

        // 카테고리 스피너
        val categoryList = listOf("마케팅", "사회현상", "브랜드", "아이디어")
        binding.formCreateCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                println(categoryList[pos] + "입니다")
                categoryId = pos + 1
                Log.d("formCreate", categoryId.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }
        }

        // 질문 추가
        binding.formCreateAddBtn.setOnClickListener {
            Log.d("formCreate", "질문 추가 버튼 선택")

            val items = arrayOf("객관식(택1)", "객관식(복수선택)", "단답형", "서술형")
            var selectedItem: String? = null

            AlertDialog.Builder(this).run {
                setTitle("질문 추가")
                setSingleChoiceItems(items, -1) { dialog, which ->
                    selectedItem = items[which]
                    Log.d("formCreate", selectedItem!!)
                }
                setPositiveButton("완료", DialogInterface.OnClickListener { dialogInterface, i ->
                    formCreateRAdapter.addItem(MyQuestion("", selectedItem!!, optionList))
                    formCreateRAdapter.notifyDataSetChanged()
                })
                setNegativeButton("취소", null)
                show()
            }

        }

        // 취소 클릭 시 FormListActivity로 이동
       binding.formCreateCancelTv.setOnClickListener {
           Log.d("formCreate", "취소 클릭")

           val intent = Intent(this, FormListActivity::class.java)
           startActivity(intent)
           finish()

       }

        // 저장
        binding.formCreateSaveTv.setOnClickListener {

        }
    }
}