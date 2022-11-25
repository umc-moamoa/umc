package com.example.umc_hackathon.survey.result

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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

class ResultFragment : Fragment(), ResultView {

    private lateinit var binding: FragmentResultBinding
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentResultBinding.inflate(layoutInflater)

        getResultDetail(1) //리팩토링

        return binding.root
    }

    private fun getResultDetail(detailId: Long) {
        val resultService = ResultService()
        resultService.setResultView(this)
//        resultService.getResult(detailId)
    }

//    private fun getJwt(): String? {
//        val spf = context?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
//        return spf!!.getString("jwt", "")
//    }

    override fun onGetResultSuccess(detailResult: DetailResult) {
        binding.formResultQuestionTv.text = detailResult.question

        if(detailResult.format == 1 || detailResult.format == 2) { //객관식
            //파이차트
            pieChart = binding.pieChart
            pieChart.setUsePercentValues(true)

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
        Log.d("result_fragment-fail", message)
    }

    override fun onGetDetailIdSuccess(result: DetailIdResponse) {
        TODO("Not yet implemented")
    }

    override fun onGetDetailIdFailure(message: String) {
        TODO("Not yet implemented")
    }

    private fun setCardView(res: String) {
        val layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(10, 10, 10, 10)

        val resCardView = context?.let { CardView(it) }
        resCardView?.layoutParams = layoutParams
        resCardView?.radius = 10F
        resCardView?.setContentPadding(10, 10, 10, 10)
        resCardView?.setCardBackgroundColor(Color.LTGRAY)
        resCardView?.cardElevation = 8F
//                resCardView?.addView(genera)

        val one = TextView(context)
        one.text = res
        one.textSize = 12f
        one.setTextColor(Color.BLACK)

        resCardView?.addView(one)

        binding.root.addView(resCardView)
    }




}