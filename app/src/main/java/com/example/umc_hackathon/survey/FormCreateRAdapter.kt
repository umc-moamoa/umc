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
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.FormDetailActivity
import com.example.umc_hackathon.R
import kotlinx.android.synthetic.main.dialog_option_item.view.*

// 설문 작성 페이지의 질문들 목록
class FormCreateRAdapter(val questionList: ArrayList<MyQuestion>): RecyclerView.Adapter<FormCreateRAdapter.MyViewHolder> (){

    fun addItem(item: MyQuestion) {
        questionList.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        questionList.removeAt(position)
        notifyDataSetChanged()
    }

    fun modifyItem(position: Int, item: MyQuestion) {
        questionList[position] = item
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

                true
            }
        }
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("FormCreateRAdapter", " - onBindViewHolder() called / position: $position")
        holder.title.setText((position + 1).toString() + "번. " + questionList.get(position).title)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.question_item_et)
    }

}