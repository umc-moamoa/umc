package com.example.umc_hackathon

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class MySurveyGridRAdapter(val surveyList: ArrayList<MySurvey>): RecyclerView.Adapter<MySurveyGridRAdapter.MyViewHolder>() {

    val TAG: String = "<MySurveyGridRAdapter>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_my_survey_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return surveyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.title.text = surveyList.get(position).title
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.board_my_survey_item_title_tv)
    }

}