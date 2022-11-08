package com.example.umc_hackathon.survey

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding
import com.example.umc_hackathon.databinding.AddItemDialogBinding
import com.example.umc_hackathon.post.FormListActivity
import kotlinx.android.synthetic.main.dialog_option_item.*
import java.util.*
import kotlin.collections.ArrayList

class FormCreateActivity : AppCompatActivity(), FormCreateView {

    private lateinit var binding: ActivityFormCreateBinding
    val builderItem by lazy {AddItemDialogBinding.inflate(layoutInflater)}

    var categoryId: Long = 1
    var shortCount: Int = 0
    var longCount: Int = 0

    var postDetail: ArrayList<String> = arrayListOf()

    var questionList = arrayListOf<MyQuestion>()
    var createRAdapter = FormCreateRAdapter(questionList)

    var optionList = arrayListOf<Option>()
    var optionRAdapter = OptionRAdapter(optionList)

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

         // 카테고리 스피너
        val categoryList = listOf("마케팅", "사회현상", "브랜드", "아이디어")
        binding.formCreateCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                println(categoryList[pos] + "입니다")
                categoryId = pos.toLong() + 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("카테고리를 선택하세요")
            }
        }

        // 설문 타입 스피너에 따라 옵션 visibility 선택
        val typeList = listOf("객관식(택1)", "객관식(복수선택)", "단답형", "서술형")
        builderItem.dialogTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                if(pos == 0 || pos == 1) {
                    builderItem.dialogOptionLl.visibility = View.VISIBLE
                    builderItem.dialogItemOptionRv.visibility = View.VISIBLE
                } else if (pos == 2 || pos == 3) {
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
        binding.formCreateListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.formCreateListRv.setHasFixedSize(true)
        binding.formCreateListRv.adapter = createRAdapter

        // 다이얼로그 질문 옵션 리사이클러뷰
        builderItem.dialogItemOptionRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        builderItem.dialogItemOptionRv.setHasFixedSize(true)
        builderItem.dialogItemOptionRv.adapter = optionRAdapter

        // 이벤트 리스너
        binding.formCreateCancelTv.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 옵션 추가
        builderItem.dialogOptionAddIv.setOnClickListener {
            Log.d("옵션 추가함 ", builderItem.dialogOptionInputEt.text.toString())

            optionRAdapter.addItem(Option(builderItem.dialogOptionInputEt.text.toString()))
            builderItem.dialogOptionInputEt.setText("") // 초기화 시키기

            Log.d("(추가) 옵션 개수", optionRAdapter.itemCount.toString())
        }

        // 다이얼로그에서 쓰는 View들
        var questionEt = builderItem.dialogQuestionEt
        var questionSpinner = builderItem.dialogTypeSpinner

        // 질문 추가
        binding.formCreateAddBtn.setOnClickListener {
            Log.d("질문 추가", " 선택")

            var question= questionEt.text.toString()
            var spinner = questionSpinner.selectedItem.toString()

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

                    Log.d("질문 저장(질문)", question)
                    Log.d("질문 저장(스피너)", spinner)

                    if(spinner == "객관식(택1)" || spinner == "객관식(복수선택)" || spinner == "단답형") {
                        shortCount += 1
                    } else if(spinner == "서술형") {
                        longCount += 1
                    }

                    createRAdapter.addItem(MyQuestion(question, spinner, optionList))
                    createRAdapter.notifyDataSetChanged()

                    Log.d("qList", optionList.toString())
                    Log.d("옵션 총 개수", optionList.size.toString())
                })
                setNegativeButton("취소", null)
                show()
            }

            optionRAdapter.clearAll()
        }

        binding.formCreateSaveTv.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("설문을 저장하시겠습니까?")
                setPositiveButton("네, 저장하겠습니다", DialogInterface.OnClickListener { dialogInterface, i ->
                    if(questionList.size >= 1) {
                        formCreate()

                        val intent = Intent(this@FormCreateActivity, FormListActivity::class.java)
                        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        finish()

                        Toast.makeText(this@FormCreateActivity, "설문을 저장했습니다", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@FormCreateActivity, "설문 질문 개수가 부족합니다", Toast.LENGTH_SHORT).show()
                    }
                })
                setNegativeButton("아니요", null)
                show()
            }
        }
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun getForm(): FormCreateRequest {
        val formTitle: String = binding.formCreateTitleEt.text.toString()
        val formContent: String = binding.formCreateContentEt.text.toString()
        val formDeadline: String = binding.formCreateDateEt.text.toString()

        val postDetails: ArrayList<ArrayList<String>> = arrayListOf()

        for(i in 0 until createRAdapter.itemCount) {
            postDetail = arrayListOf() // 초기화

            if(questionList[i].type == "객관식(택1)") {
                postDetail.add("1")
                postDetail.add(questionList[i].title)
                for(j in 0 until questionList[i].option!!.size) {
                    postDetail.add(questionList[i].option!![j].question)
                }
            } else if (questionList[i].type == "객관식(복수선택)") {
                postDetail.add("2")
                postDetail.add(questionList[i].title)
                for(j in 0 until questionList[i].option!!.size) {
                    postDetail.add(questionList[i].option!![j].question)
                }
            } else if (questionList[i].type == "단답형") {
                postDetail.add("3")
                postDetail.add(questionList[i].title)
            } else if (questionList[i].type == "서술형") {
                postDetail.add("4")
                postDetail.add(questionList[i].title)
            }

            postDetails.add(postDetail)
        }

        return FormCreateRequest(categoryId, shortCount, longCount, formTitle, formContent, formDeadline, postDetails)
    }

    private fun formCreate() {
        val formService = FormService()
        formService.setFormCreateView(this)
        formService.formCreate(getForm(), getJwt()!!)
    }

    override fun onFormCreateSuccess() {
        Toast.makeText(this, "설문 등록에 성공했습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onFormCreateFailure(code: Int) {
        when(code) {
            2013 -> Toast.makeText(this, "포인트가 부족합니다", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "설문 등록에 실패했습니다", Toast.LENGTH_SHORT).show()
        }
    }
}