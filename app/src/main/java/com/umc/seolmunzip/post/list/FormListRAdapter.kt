package com.umc.seolmunzip.post.list

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.umc.seolmunzip.R
import com.umc.seolmunzip.post.detail.PostDetailActivity

class FormListRAdapter(val postList: List<PostList>): RecyclerView.Adapter<FormListRAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_list_item, parent, false)
        Log.d("onCreateViewHolder() / ", "FormListRAdapter에서 메소드 called")

        return MyViewHolder(view).apply {
            itemView.setOnClickListener {
//                val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(itemView?.context, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList[adapterPosition].postId)
                Log.d("list_item_post_id", postList[adapterPosition].postId.toString());
                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("onBindViewHolder() / ", " called / position: $position")

        if (postList[position].status == "ACTIVE") {
            if (postList[position].dday == 0) {
                holder.dday.text = "D - DAY"
            }
            else {
                holder.dday.text = "D - " + postList[position].dday.toString()
            }
        }
        else {
            holder.dday.text = "마감"
        }
        holder.title.text = postList[position].title
        holder.qCount.text = postList[position].qcount.toString() + "개의 항목"
        holder.point.text = postList[position].point.toString() + "P"
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.board_list_item_title_tv)
        val qCount: TextView = itemView.findViewById(R.id.board_list_item_count_tv)
        val point: TextView = itemView.findViewById(R.id.board_list_item_coin_tv)
        val dday: TextView = itemView.findViewById(R.id.board_list_item_deadline_tv)
    }

}