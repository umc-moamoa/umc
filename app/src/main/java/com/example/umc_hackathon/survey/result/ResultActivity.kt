package com.example.umc_hackathon.survey.result

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_hackathon.R
import com.example.umc_hackathon.databinding.ActivityResultBinding
import com.example.umc_hackathon.my.survey.MySurveyActivity
import com.example.umc_hackathon.my.survey.MySurveyList
import com.example.umc_hackathon.my.survey.MySurveyResponse
import com.example.umc_hackathon.my.survey.MySurveyView
import com.example.umc_hackathon.post.PostService
import com.example.umc_hackathon.survey.AnswerRAdapter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF

class ResultActivity : AppCompatActivity(), ResultView, MySurveyView {

    private var postId: Long = 0L
    private var postTitle: String = ""
    private var startId: Long = 0L
    private var endId: Long = 0L
    private var pos: Int = 0
    private lateinit var binding: ActivityResultBinding
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getResponseCount()

        if(intent.hasExtra("postId")) {
            postId = intent.getLongExtra("postId", postId)
            postTitle = intent.getStringExtra("postTitle")!!
            Log.d("postId", " : " + postId)
            Log.d("postTitle", postTitle)
            binding.formResultTitleTv.text = postTitle
            getResultDetail(postId) //리팩토링
        }
        binding.resultAnswerRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.resultAnswerRv.setHasFixedSize(true)

        binding.formResultGoFormMainLl.setOnClickListener {
            val intent = Intent(this, MySurveyActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

        binding.formResultNextBtn.setOnClickListener {
            pos++
            if (pos <= endId) {
                getResult(pos.toLong())
            }
            else {
                Toast.makeText(this, "마지막 페이지입니다.", Toast.LENGTH_LONG).show()
            }
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

        Log.d("detail post id", detailId.toString())

//        for (i in startId..endId)
//        {
//        resultService.getResult(52)
        resultService.getResult(detailId)
//        }
    }

    private fun getResponseCount() {
        val postService = PostService()
        postService.setMySurveyView(this)
        postService.getMySurvey(getAccessToken().toString(), getRefreshToken().toString())

        Log.d("getResponseCount", " / ResultActivity 에서 메소드")
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    override fun onGetResultSuccess(detailResult: DetailResult) {
        binding.formResultQuestionTv.text = detailResult.question
//        binding.formResultCountTv.text = detailResult.res.size.toString() + "명 응답"

        if(detailResult.format == 1 || detailResult.format == 2) { //객관식
            binding.pieChart.visibility = View.VISIBLE
            binding.resultAnswerRv.visibility = View.INVISIBLE
            //파이차트
            pieChart = binding.pieChart
            pieChart.setUsePercentValues(true)
            pieChart.description.isEnabled = false
//            pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
//            pieChart.dragDecelerationFrictionCoef = 0.95f
            pieChart.isDrawHoleEnabled = true
//            pieChart.setHoleColor(Color.WHITE)
//            pieChart.setBackgroundColor(color)
//            pieChart.setTransparentCircleColor(Color.WHITE)
//            pieChart.setTransparentCircleAlpha(110)
//            pieChart.holeRadius = 58f
//            pieChart.transparentCircleRadius = 61f
//            pieChart.setDrawCenterText(true)
//            pieChart.rotationAngle = 0f
//            pieChart.isRotationEnabled = true
//            pieChart.isHighlightPerTapEnabled = true
//            pieChart.legend.isEnabled = false
//            pieChart.setEntryLabelColor(Color.WHITE)
//            pieChart.setEntryLabelTextSize(12f)

            val visitors: ArrayList<PieEntry> = ArrayList()
            for (i in 0 until detailResult.resultItem.size) {
                visitors.add(PieEntry(detailResult.statistics[i], detailResult.resultItem[i].item))
                Log.d("detail-result", detailResult.resultItem[i].item)
            }
            pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic)

            var pieDataSet = PieDataSet(visitors, "")
//            pieDataSet.sliceSpace = 3f
//            pieDataSet.iconsOffset = MPPointF(0f, 40f)
//            pieDataSet.selectionShift = 5f
            val mColors: ArrayList<Int> = ArrayList()
//            val mColors: ArrayList<Int> = listOf(
//                R.color.baby_pink, R.color.pale, R.color.pale_peach, R.color.light_blue_grey,
//                R.color.powder_blue, R.color.light_periwinkle, R.color.liget_grey_blue, R.color.pig_pink
//            )
            mColors.add(R.color.baby_pink)
            mColors.add(R.color.pale)
            mColors.add(R.color.pale_peach)
            mColors.add(R.color.light_blue_grey)
            mColors.add(R.color.pig_pink)
            mColors.add(R.color.powder_blue)
            mColors.add(R.color.light_periwinkle)
            mColors.add(R.color.liget_grey_blue)

//            val colors: ArrayList<Int> = ArrayList()
//            for (i in 0 until detailResult.resultItem.size) {
//                colors.add(mColors[i])
//                Log.d("color", mColors[i].toString())
//            }

//            pieDataSet.colors = mColors
//            for (i in 0..8) {
//                pieDataSet.colors.add(VORDIPLOM_COLORS[i])
//            }
//            pieDataSet.colors = ColorTemplate.JOYFUL_COLORS

            with(pieDataSet) {
                sliceSpace = 3f
                iconsOffset = MPPointF(0f, 40f)
                pieDataSet.selectionShift = 5f
//                setColors(mColors)
//                setColors(*ColorTemplate.JOYFUL_COLORS)
                setColors(*ColorTemplate.MATERIAL_COLORS)
            }

            val pieData = PieData(pieDataSet)
            pieData.setValueFormatter(PercentFormatter())
            pieData.setValueTextSize(12f)
            pieData.setValueTypeface(Typeface.DEFAULT_BOLD)
            pieData.setValueTextColor(Color.WHITE)
            pieChart.data = pieData

            pieChart.animation
            pieChart.invalidate()
        }
        else { //주관식
            binding.pieChart.visibility = View.INVISIBLE
            binding.resultAnswerRv.visibility = View.VISIBLE

            binding.resultAnswerRv.adapter = AnswerRAdapter(detailResult.res)
        }
    }

    override fun onGetResultFailure(message: String) {
        Log.d("result_get-fail", message)
    }

    override fun onGetDetailIdSuccess(result: DetailIdResponse) {
        startId = result.result.startId
        endId = result.result.endId
        pos = startId.toInt()

        getResult(startId)
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

    override fun onGetMySurveyViewSuccess(mySurveyList: MySurveyResponse) {
        for(i in mySurveyList.result) {
            if (i.postId == postId) {
                binding.formResultCountTv.text = i.postResultCount.toString() + "명 응답"
                Log.d("포스트 아이디", "postId : " + i.postId.toString() + " postResultCount : " + i.postResultCount + " 이 게시글의 posId : " + postId.toString())
            }
        }
        Toast.makeText(this, "나의 설문조사 응답 명수 가져오기", Toast.LENGTH_SHORT).show()
    }

    override fun onGetMySurveyViewFailure(mySurveyList: MySurveyResponse) {
        TODO("Not yet implemented")
    }

}