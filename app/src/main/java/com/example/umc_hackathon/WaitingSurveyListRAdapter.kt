package com.example.umc_hackathon

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WaitingSurveyListRAdapter(val surveyList: ArrayList<MySurvey>): RecyclerView.Adapter<WaitingSurveyListRAdapter.MyViewHolder>()  {

    val TAG: String = "<WaitingSLRAdapter>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaitingSurveyListRAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.waiting_survey_board_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return surveyList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.waiting_survey_board_item_title_tv)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.title.text = surveyList.get(position).title
    }

}
