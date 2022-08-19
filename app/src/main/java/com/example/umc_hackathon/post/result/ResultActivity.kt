package com.example.umc_hackathon.post.result

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.FragmentResultBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

class ResultActivity : AppCompatActivity(), ResultView {

    private var postId: Long = 0L
    private lateinit var binding: FragmentResultBinding
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("postId")) {
            postId = intent.getLongExtra("postId", postId)
            Log.d("postId", " : " + postId)
            getResultDetail(postId) //리팩토링
        }
    }

    private fun getResultDetail(postId: Long) {
        val resultService = ResultService()
        resultService.setResultView(this)
        resultService.getDetailId(postId)
    }

    private fun getResult(detailId: Long) {
        val resultService = ResultService()
        resultService.setResultView(this)
//        resultService.getResult(detailId) 테스트 후 복구
        resultService.getResult(52)
    }

    override fun onGetResultSuccess(detailResult: DetailResult) {
        binding.formResultQuestionTv.text = detailResult.question

        if(detailResult.format == 1 || detailResult.format == 2) { //객관식
            //파이차트
            pieChart = binding.pieChart
            pieChart.setUsePercentValues(true)
            pieChart.description.isEnabled = false
            pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
            pieChart.dragDecelerationFrictionCoef = 0.95f
            pieChart.isDrawHoleEnabled = true
            pieChart.setHoleColor(Color.WHITE)
            pieChart.setTransparentCircleColor(Color.WHITE)
            pieChart.setTransparentCircleAlpha(110)
            pieChart.holeRadius = 58f
            pieChart.transparentCircleRadius = 61f
            pieChart.setDrawCenterText(true)
            pieChart.rotationAngle = 0f
            pieChart.isRotationEnabled = true
            pieChart.isHighlightPerTapEnabled = true
            pieChart.legend.isEnabled = false
            pieChart.setEntryLabelColor(Color.WHITE)
            pieChart.setEntryLabelTextSize(12f)

            val visitors: ArrayList<PieEntry> = ArrayList()
            visitors.add(PieEntry(detailResult.case1.toFloat(), "1"))
            visitors.add(PieEntry(detailResult.case2.toFloat(), "2"))
            visitors.add(PieEntry(detailResult.case3.toFloat(), "3"))
            visitors.add(PieEntry(detailResult.case4.toFloat(), "4"))
            visitors.add(PieEntry(detailResult.case5.toFloat(), "5"))
            visitors.add(PieEntry(detailResult.case6.toFloat(), "6"))
            visitors.add(PieEntry(detailResult.case7.toFloat(), "7"))
            visitors.add(PieEntry(detailResult.case8.toFloat(), "8"))

            val pieDataSet = PieDataSet(visitors, "visitors")

            val colors: ArrayList<Int> = ArrayList()
            colors.add(R.color.baby_pink)
            colors.add(R.color.pale)
            colors.add(R.color.pale_peach)
            colors.add(R.color.light_blue_grey)
            colors.add(R.color.pig_pink)
            colors.add(R.color.powder_blue)
            colors.add(R.color.light_periwinkle)
            colors.add(R.color.liget_grey_blue)

            pieDataSet.colors = colors

            val pieData = PieData(pieDataSet)
            pieData.setValueFormatter(PercentFormatter())
            pieData.setValueTextSize(15f)
            pieData.setValueTypeface(Typeface.DEFAULT_BOLD)
            pieData.setValueTextColor(Color.WHITE)
            pieChart.data = pieData

            pieChart.animation

            pieChart.invalidate()
        }
        else { //주관식
            for (res in detailResult.res) {
                setCardView(res.result)
            }
        }
    }

    override fun onGetResultFailure(message: String) {
        Log.d("result_get-fail", message)
    }

    override fun onGetDetailIdSuccess(result: DetailIdResponse) {
        getResult(result.result.startId)
    }

    override fun onGetDetailIdFailure(message: String) {
        Log.d("result_detailId-fail", message)
    }

    private fun setCardView(res: String) {
        val layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(10, 10, 10, 10)

        val resCardView = CardView(this)
        resCardView.layoutParams = layoutParams
        resCardView.radius = 10F
        resCardView.setContentPadding(10, 10, 10, 10)
        resCardView.setCardBackgroundColor(Color.LTGRAY)
        resCardView.cardElevation = 8F
//                resCardView?.addView(genera)

        val one = TextView(this)
        one.text = res
        one.textSize = 12f
        one.setTextColor(Color.BLACK)

        resCardView?.addView(one)

        binding.root.addView(resCardView)
    }

}
