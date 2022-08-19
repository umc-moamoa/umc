package com.example.umc_hackathon.survey

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R


class OptionRAdapter(val optionList: ArrayList<String>): RecyclerView.Adapter<OptionRAdapter.MyViewHolder> (){

    fun addItem(item: String) {
        optionList.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        optionList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dialog_option_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("OptionRAdapter", " - onBindViewHolder() called / position: $position")
        holder.option.text = optionList[position]
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val option: TextView = itemView.findViewById<TextView>(R.id.dialog_option_item_et)
    }

}