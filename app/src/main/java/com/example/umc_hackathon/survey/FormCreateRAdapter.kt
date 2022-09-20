package com.example.umc_hackathon.survey

import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.FormDetailActivity
import com.example.umc_hackathon.R
import kotlinx.coroutines.NonDisposableHandle.parent

// 설문 작성 페이지의 질문들 목록
class FormCreateRAdapter(val questionList: ArrayList<MyQuestion>): RecyclerView.Adapter<FormCreateRAdapter.MyViewHolder> (){

    fun addItem(item: MyQuestion) {
        Log.d("formCreateAdapter", "추가 메소드 호출")

        questionList.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        Log.d("formCreateAdapter", "삭제 메소드 호출")

        questionList.removeAt(position)
        notifyDataSetChanged()
    }

    fun modifyItem(position: Int, item: MyQuestion) {
        Log.d("formCreateAdapter", "수정 메소드 호출")

        questionList[position] = item
    }

    fun clearAll() {
        Log.d("formCreateAdapter", "클리어 메소드 호출")

        questionList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_question_item, parent, false)
        return MyViewHolder(view).apply {
            itemView.setOnLongClickListener {
                AlertDialog.Builder(parent.context).run {
                    setTitle("해당 질문을 삭제하시겠습니까?")
                    setPositiveButton("네, 삭제할게요", DialogInterface.OnClickListener { dialogInterface, i ->
                        removeItem(adapterPosition)
                    })
                    setNegativeButton("아니요", null)
                    show()
                }

                false
            }
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("FormCreateRAdapter", "onBindViewHolder() called / position: $position")

        holder.title.text = (position + 1).toString() + "번. " + questionList[position].title

        if (questionList[position].type == "객관식(택1)" || questionList[position].type == "객관식(복수선택)") {
            holder.option.visibility = View.GONE
            holder.optionLl.visibility = View.VISIBLE

            var optionList = arrayListOf<Option>()
            var optionRAdapter = OptionRAdapter(optionList)

            // 옵션 어댑터
            holder.optionRv.layoutManager = LinearLayoutManager(holder.optionRv.context, LinearLayoutManager.VERTICAL, false)
            holder.optionRv.setHasFixedSize(true)
            holder.optionRv.adapter = optionRAdapter

            // 이벤트 리스너 등록
            holder.optionAdd.setOnClickListener {
                optionRAdapter.addItem(Option("추가된 옵션"))
                optionRAdapter.notifyDataSetChanged()
            }


//            var result: String = questionList[position].type
//            for(i in 0 until questionList[position].option!!.size) {
//                result += "${i + 1}번 " + questionList[position].option!![i].question + "\n"
//            }
//            holder.option.text = result
        } else {
            holder.option.visibility = View.VISIBLE
            holder.optionLl.visibility = View.GONE

            holder.option.text = "${questionList[position].type} 입니다."
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_item_et)
        val option = itemView.findViewById<TextView>(R.id.question_item_option_tv)
        val optionLl = itemView.findViewById<LinearLayout>(R.id.question_item_add_option_ll)
        val optionAdd = itemView.findViewById<TextView>(R.id.question_item_add_option_tv)
        val optionRv = itemView.findViewById<RecyclerView>(R.id.question_item_add_option_rv)
    }

}