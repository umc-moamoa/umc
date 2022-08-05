package com.example.umc_hackathon.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.MySurvey
import com.example.umc_hackathon.R
import com.example.umc_hackathon.temporary.WaitingSurveyListRAdapter
import com.example.umc_hackathon.databinding.FragmentFormListBrandBinding

class FormListMarketing : Fragment() {

    var modelList = ArrayList<MySurvey>()
    private var linearLayoutManager: RecyclerView.LayoutManager? = null
    private var recyclerAdapter: RecyclerView.Adapter<WaitingSurveyListRAdapter.MyViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentFormListBrandBinding.inflate(layoutInflater)

//        // 리스트 생성
//        for (i in 1..10){
//            val mySurvey = MySurvey(title = "브랜드 $i")
//            this.modelList.add(mySurvey)
//        }
//
//        val view = inflater!!.inflate(R.layout.fragment_form_list_brand, container, false)
//        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_brand_rv)
//
//        recyclerAdapter = WaitingSurveyListRAdapter(modelList)
//        linearLayoutManager = LinearLayoutManager(activity)
//
//        recyclerView.layoutManager = linearLayoutManager
//        recyclerView.adapter = recyclerAdapter
//        recyclerView.setHasFixedSize(true)

        return view
    }

}