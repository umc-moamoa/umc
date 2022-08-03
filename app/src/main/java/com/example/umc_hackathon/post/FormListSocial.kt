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
import com.example.umc_hackathon.databinding.FragmentFormListSocialBinding

class FormListSocial : Fragment() {

    var modelList = ArrayList<MySurvey>()
    private var linearLayoutManager: RecyclerView.LayoutManager? = null
    private var recyclerAdapter: RecyclerView.Adapter<WaitingSurveyListRAdapter.MyViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentFormListSocialBinding.inflate(layoutInflater)

        // 리스트 생성
        for (i in 1..10){
            val mySurvey = MySurvey(title = "사회현상 $i")
            this.modelList.add(mySurvey)
        }

//        binding.fragmentMarketingRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        binding.fragmentMarketingRv.setHasFixedSize(true)
//        binding.fragmentMarketingRv.adapter = WaitingSurveyListRAdapter(modelList)

        val view = inflater!!.inflate(R.layout.fragment_form_list_social, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_social_rv)

        recyclerAdapter = WaitingSurveyListRAdapter(modelList)
        linearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)

        return view
    }

}