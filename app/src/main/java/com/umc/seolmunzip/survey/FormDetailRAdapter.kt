package com.umc.seolmunzip.survey

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.umc.seolmunzip.R
import com.umc.seolmunzip.survey.participate.FormInputItem

class FormDetailRAdapter(private val questionList: List<FormDetail>, private val formInputItem: FormInputItem): RecyclerView.Adapter<FormDetailRAdapter.MyViewHolder> () {

    private var tag= "FormDetailRAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_input_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(tag, "onBindViewHolder() position: $position")
        holder.title.text = (position + 1).toString() + "번 " + questionList[position].question

        val iCount = questionList[position].items.size

        // 질문 유형(객관식, 주관식)에 따라 다르게 보이기
        when(questionList[position].format) {
            1 -> {
                val radioGroup = RadioGroup(holder.itemView.context)
                holder.answerLayout.addView(radioGroup)

                for (i in 0 until iCount) {
                    val radioButton = RadioButton(holder.itemView.context)
                    radioButton.id = i + 1
                    radioButton.text = questionList[position].items[i]
                    radioGroup.addView(radioButton)

                    var answerId: ArrayList<String> = arrayListOf()
                    answerId.add(questionList[position].postDetailId.toString())
                    var answerList: ArrayList<String> = arrayListOf()

                    radioButton.setOnClickListener {
                        answerList.add(radioButton.id.toString())
                        var answer: ArrayList<ArrayList<String>> = arrayListOf(answerId, answerList)
                        Log.d("선택한 답변", answer.toString() + radioButton.id.toString())
                        formInputItem.onInputItem(answer)
                    }
                }
            }
            2 -> {
                var checkList: ArrayList<String> = arrayListOf()

                for(i in 0 until iCount) {
                    var answerId: ArrayList<String> = arrayListOf()
                    var answerList: ArrayList<String>
                    val checkBox = CheckBox(holder.itemView.context)
                    checkBox.text = questionList[position].items[i]
                    holder.answerLayout.addView(checkBox)

                    answerId.add(questionList[position].postDetailId.toString())
                    checkBox.setOnCheckedChangeListener { compoundButton, b ->
                        if(compoundButton.isChecked) {
                            checkList.add((i + 1).toString())
                            Log.d("체크리스트", checkList.toString())
                        } else {
                            checkList.remove((i + 1).toString())
                            Log.d("체크리스트", checkList.toString())
                        }
                        answerList = checkList

                        var answer: ArrayList<ArrayList<String>> = arrayListOf(answerId, answerList)
                        Log.d("선택한 답변", answer.toString() + i + "번째 옵션")
                        formInputItem.onInputItem(answer)
                    }
                }
            }
            3 -> {
                val editText = EditText(holder.itemView.context)
                val button = Button(holder.itemView.context)

                button.text = "답변 저장"
                holder.answerLayout.addView(editText)
                holder.answerLayout.addView(button)

                var answerId: ArrayList<String> = arrayListOf()
                answerId.add(questionList[position].postDetailId.toString())
                var answerList: ArrayList<String>

                button.setOnClickListener {
                    answerList = arrayListOf(editText.text.toString())

                    var answer: ArrayList<ArrayList<String>> = arrayListOf(answerId, answerList)
                    Log.d("선택한 답변", answer.toString())
                    formInputItem.onInputItem(answer)
                }
            }
            4 -> {
                val editText = EditText(holder.itemView.context)
                val button = Button(holder.itemView.context)

                button.text = "답변 저장"
                holder.answerLayout.addView(editText)
                holder.answerLayout.addView(button)

                var answerId: ArrayList<String> = arrayListOf()
                answerId.add(questionList[position].postDetailId.toString())
                var answerList: ArrayList<String>

                button.setOnClickListener {
                    answerList = arrayListOf(editText.text.toString())
                    var answer: ArrayList<ArrayList<String>> = arrayListOf(answerId, answerList)
                    Log.d("선택한 답변", answer.toString())

                    formInputItem.onInputItem(answer)
                }
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.question_input_item_tv)
        val answerLayout: LinearLayout = itemView.findViewById(R.id.question_input_item_ll)
    }

}