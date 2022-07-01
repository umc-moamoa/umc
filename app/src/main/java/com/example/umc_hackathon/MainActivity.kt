package com.example.umc_hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MyRecyclerviewInterface {

    val TAG: String = "log"

    // 데이터를 담을 그릇 즉 배열
    var modelList = ArrayList<MyModel>()

    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    // 뷰가 화면에 그려질때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 10번 반복한다.
        for (i in 1..10){
            val myModel = MyModel(title = "사회현상에 대한 소비자 인식 $i")
            this.modelList.add(myModel)
        }

        Log.d(TAG, "MainActivity - ${this.modelList.size}")

        // 어답터 인스턴스 생성
        myRecyclerAdapter = MyRecyclerAdapter(this)

        myRecyclerAdapter.submitList(this.modelList)

        // 리사이클러뷰 설정
        my_recycler_view.apply {

            // 리사이클러뷰 방향 등 설정
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            // 어답터 장착
            adapter = myRecyclerAdapter
        }

    }

    override fun onItemClicked(position: Int) {
        Log.d(TAG, "MainActivity - onItemClicked() called / position: $position")

        var title: String? = null

        // 값이 비어있으면 ""를 넣는다.
        // unwrapping - 언랩핑

        val titleClick: String = this.modelList[position].title ?: ""

        AlertDialog.Builder(this)
            .setTitle(titleClick)
            .setMessage("$titleClick")
            .setPositiveButton("닫기") { dialog, id ->
                Log.d(TAG, "MainActivity - 다이얼로그 확인 버튼 클릭했음")
            }
            .show()

    }

}