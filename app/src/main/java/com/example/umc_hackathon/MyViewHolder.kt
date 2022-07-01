package com.example.umc_hackathon

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.board_list_item.view.*

// 커스텀 뷰홀더
class MyViewHolder(itemView: View,
                   recyclerviewInterface: MyRecyclerviewInterface):
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener
{

    val TAG: String = "log"

    private val mainTextView = itemView.title_main_text

    private var myRecyclerviewInterface : MyRecyclerviewInterface? = null

    // 기본 생성자
    init {
        itemView.setOnClickListener(this)
        this.myRecyclerviewInterface = recyclerviewInterface

    }


    // 데이터와 뷰를 묶는다.
    fun bind(myModel: MyModel) {
        // 텍스트뷰 와 실제 텍스트 데이터를 묶는다.
        mainTextView.text = myModel.title
    }

    override fun onClick(p0: View?) {
        this.myRecyclerviewInterface?.onItemClicked(adapterPosition)
    }


}