package com.example.umc_hackathon.post

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.R
import com.example.umc_hackathon.temporary.SurveyListResult

class WaitingSurveyListRAdapter(val postList: List<PostList>): RecyclerView.Adapter<WaitingSurveyListRAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_waiting_survey_item, parent, false)
        Log.d("onCreateViewHolder() / ", "WaitingSurveyListRAdapter 클래스의 메소드에서 called")

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("onBindViewHolder() / ", " called / position: $position")

        holder.title.text = postList[position].title
        holder.qCount.text = postList[position].qCount.toString() + "개의 항목"
        holder.point.text = postList[position].point.toString() + "P"
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.board_waiting_survey_item_title_tv)
        val qCount: TextView = itemView.findViewById(R.id.board_waiting_survey_item_count_tv)
        val point: TextView = itemView.findViewById(R.id.board_waiting_survey_item_point_tv)
    }
}
