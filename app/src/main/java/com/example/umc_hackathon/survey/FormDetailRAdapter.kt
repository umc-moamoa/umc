package com.example.umc_hackathon.survey

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R
import java.util.concurrent.LinkedBlockingDeque

// 설문 조사 문항 확인
class FormDetailRAdapter(val questionList: List<FormDetail>): RecyclerView.Adapter<FormDetailRAdapter.MyViewHolder> () {

    private final var TAG = "FormDetailRAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_input_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder() called / position: $position")
        holder.title.text = (position + 1).toString() + "번. " + questionList[position].question

        val iCount = questionList[position].items.size

        // 질문 유형에 따라 다르게 보이기
        if(questionList[position].format == 1) {
            val iId = questionList[position].postDetailId
            val radioGroup: RadioGroup = RadioGroup(holder.itemView.context)
            holder.answerLayout.addView(radioGroup)

            for(i in 0 until iCount) {
                val radioButton: RadioButton = RadioButton(holder.itemView.context)
                radioButton.text = questionList[position].items[i]
                radioGroup.addView(radioButton)
            }


            Log.d("아이템 개수(단독선택)", iCount.toString())
            Log.d("아이템 아이디(단독선택)", iId.toString())
        } else if(questionList[position].format == 2) {
            val iId = questionList[position].postDetailId

            for(i in 0 until iCount) {
                val checkBox: CheckBox = CheckBox(holder.itemView.context)
                checkBox.text = questionList[position].items[i]
                holder.answerLayout.addView(checkBox)
            }

            Log.d("아이템 개수(복수선택)", iCount.toString())
            Log.d("아이템 아이디(복수선택)", iId.toString())
        } else if (questionList[position].format == 3) {
            val iId = questionList[position].postDetailId
            val editText: EditText = EditText(holder.itemView.context)
            val button: Button = Button(holder.itemView.context)
            button.text = "답변 저장"
            holder.answerLayout.addView(editText)
            holder.answerLayout.addView(button)

            button.setOnClickListener {
                Log.d("작성한 답변", editText.text.toString())
                val answer = editText.text.toString()
            }

            Log.d("아이템 내용(단답)", editText.text.toString())
            Log.d("아이템 아이디(단답)", iId.toString())
        } else if (questionList[position].format == 4) {
            val iId = questionList[position].postDetailId
            val editText: EditText = EditText(holder.itemView.context)
            val button: Button = Button(holder.itemView.context)
            button.text = "답변 저장"
            holder.answerLayout.addView(editText)
            holder.answerLayout.addView(button)

            button.setOnClickListener {
                Log.d("작성한 답변", editText.text.toString())
                val answer = editText.text.toString()
            }

            Log.d("아이템 내용(서술)", editText.text.toString())
            Log.d("아이템 아이디(서술)", iId.toString())
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_input_item_tv)
        val answerLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.question_input_item_ll)
    }

}