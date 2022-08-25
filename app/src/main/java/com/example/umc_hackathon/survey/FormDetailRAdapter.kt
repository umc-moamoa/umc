package com.example.umc_hackathon.survey

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R

// 설문 조사 문항 확인
class FormDetailRAdapter(val questionList: List<FormDetail>, val itemClick: (List<String>) -> Unit): RecyclerView.Adapter<FormDetailRAdapter.MyViewHolder> () {

    var answerId: String = ""
    var answerOption = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_input_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("FormDetailRAdapter", "onBindViewHolder() called / position: $position")

        holder.title.text = (position + 1).toString() + "번. " + questionList[position].question
        
        answerId = questionList[position].postDetailId.toString() // 질문 postDetailId 넣기
//        answerOption = arrayListOf<String>() // 옵션 초기화
        
        Log.d("현재 질문의 아이디", answerId)

        // 질문 유형에 따라 다르게 보이기
        if(questionList[position].format == 1) {
            for(i in 0 until questionList[position].items.size) {
                val radioButton: RadioButton = RadioButton(holder.itemView.context)
                radioButton.text = questionList[position].items[i]
                holder.optionRg.addView(radioButton)

                radioButton.setOnClickListener {
                    if(radioButton.isChecked) {
                        Log.d("radioButton 클릭", radioButton.text.toString())
//                        answerOption.add(radioButton.text.toString()) // 클릭된거 넣기
//
//                        itemClick(answerOption)
                    }

//                    Log.d("answerOption", answerOption.toString())
                }
            }

            holder.optionRg.visibility = View.VISIBLE
            holder.optionCb.visibility = View.GONE
            holder.optionEt.visibility = View.GONE
        } else if(questionList[position].format == 2) {
            for(i in 0 until questionList[position].items.size) {
                val checkBox: CheckBox = CheckBox(holder.itemView.context)
                checkBox.text = questionList[position].items[i]
                holder.optionCb.addView(checkBox)

                checkBox.setOnClickListener {
                    if(checkBox.isChecked) {
                        Log.d("checkBox 클릭", checkBox.text.toString())
//                        answerOption.add(checkBox.text.toString()) // 클릭된거 넣기
                    }

//                    Log.d("answerOption", answerOption.toString())
                }
            }

            holder.optionRg.visibility = View.GONE
            holder.optionCb.visibility = View.VISIBLE
            holder.optionEt.visibility = View.GONE
        } else if (questionList[position].format == 3 || questionList[position].format == 4) {
            holder.optionEt.setOnEditorActionListener { textView, i, keyEvent ->
                if(i === EditorInfo.IME_ACTION_DONE) {
                    answerOption.add(holder.optionEt.text.toString())
                    Log.d("주관식 대답", holder.optionEt.text.toString())
                }

                itemClick(answerOption)
                false

            }


            
            holder.optionRg.visibility = View.GONE
            holder.optionCb.visibility = View.GONE
            holder.optionEt.visibility = View.VISIBLE
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_input_item_tv)
        val optionRg = itemView.findViewById<RadioGroup>(R.id.question_input_item_rg)
        val optionCb = itemView.findViewById<LinearLayout>(R.id.question_input_checkbox_ll)
        val optionEt = itemView.findViewById<EditText>(R.id.question_input_answer_et)
    }

}

