package com.example.umc_hackathon

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView


class MyRecyclerAdapter(private val surveyList: List<Post>): RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    val TAG: String = "<MyRecycleAdapter>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_list_item, parent, false)
        return MyViewHolder(view).apply {
            itemView.setOnClickListener {
                val currentPosition: Int = adapterPosition
                val survey: Post = surveyList.get(currentPosition)
//                Toast.makeText(parent.context, "설문조사 제목: ${survey.title}", Toast.LENGTH_SHORT).show()

                val intent = Intent(itemView?.context, FormDetailActivity::class.java)
                intent.putExtra("list_title", survey.title.toString())
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return surveyList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.title.text = surveyList.get(position).title
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_main_text)
    }

}