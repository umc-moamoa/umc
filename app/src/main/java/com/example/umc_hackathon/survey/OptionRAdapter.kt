package com.example.umc_hackathon.survey

import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R
import kotlinx.android.synthetic.main.dialog_option_item.view.*


class OptionRAdapter(var optionList: ArrayList<Option>): RecyclerView.Adapter<OptionRAdapter.MyViewHolder> (){

    fun addItem(item: Option) {
        optionList.add(item)
        notifyDataSetChanged()
        Log.d("옵션 추가", item.question)
    }

    fun removeItem(position: Int) {
        Log.d("옵션 삭제", optionList[position].question)
        optionList.removeAt(position)
        notifyDataSetChanged()
    }

    fun modifyItem(position: Int, item: Option) {
        optionList[position] = item
        notifyDataSetChanged()
        Log.d("옵션 수정", item.question)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dialog_option_item, parent, false)
        return MyViewHolder(view).apply {
            itemView.setOnLongClickListener {
                removeItem(adapterPosition)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("OptionRAdapter", " - onBindViewHolder() called / position: $position")
        holder.optionTitle.setText(optionList[position].question)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionTitle: EditText = itemView.findViewById(R.id.dialog_option_item_et)
//        val optionDetail: TextView = itemView.findViewById(R.id.dialog_option_tv)
    }

}