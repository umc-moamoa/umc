package com.example.umc_hackathon

import android.content.Intent
import android.graphics.PointF.length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.databinding.ActivityMyPointBinding

class MyPointActivity : AppCompatActivity() {

    val TAG: String = "<MyPointActivity>"
    var pointList = ArrayList<MyPoint>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMyPointBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리스트 생성
        this.pointList.add(MyPoint("사용", "2022.07.19", -2000))
        this.pointList.add(MyPoint("적립", "2022.07.19", +1100))
        this.pointList.add(MyPoint("적립", "2022.06.21", +400))
        this.pointList.add(MyPoint("적립", "2022.06.17", +400))
        this.pointList.add(MyPoint("사용", "2022.05.03", -700))
        this.pointList.add(MyPoint("사용", "2022.07.19", -2000))
        this.pointList.add(MyPoint("적립", "2022.07.19", +1100))
        this.pointList.add(MyPoint("적립", "2022.06.21", +400))
        this.pointList.add(MyPoint("적립", "2022.06.17", +400))
        this.pointList.add(MyPoint("사용", "2022.05.03", -700))

        binding.myPointPointListRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.myPointPointListRv.setHasFixedSize(true)
        binding.myPointPointListRv.adapter = MyPointRAdapter(pointList)

        var total = 0
        for (i in 0 until pointList.size) {
            total += pointList.get(i).point
        }

        binding.myPointTotalTv.text = total.toString() + "P"

        binding.myPointGoMyPageLl.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}