package com.example.umc_hackathon.post

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.FormDetailActivity
import com.example.umc_hackathon.R


class FormListRAdapter(val postList: List<Post>): RecyclerView.Adapter<FormListRAdapter.MyViewHolder>() {

    val TAG: String = "<FormListRAdapter>"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_list_item, parent, false)
        return MyViewHolder(view).apply {
            itemView.setOnClickListener {
                val currentPosition: Int = adapterPosition
                val survey: Post = postList.get(currentPosition)
                Toast.makeText(parent.context, "설문조사 제목: ${survey.title}", Toast.LENGTH_SHORT).show()

                val intent = Intent(itemView?.context, FormDetailActivity::class.java)
                intent.putExtra("title", survey.title)
                intent.putExtra("content", survey.content)
                intent.putExtra("deadline", survey.deadline)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, " - onBindViewHolder() called / position: $position")
        holder.title.text = postList.get(position).title
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.board_list_item_title_tv)
    }

}