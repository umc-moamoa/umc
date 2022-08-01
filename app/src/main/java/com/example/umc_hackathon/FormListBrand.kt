package com.example.umc_hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_hackathon.databinding.ActivityFormCreateBinding.inflate
import com.example.umc_hackathon.databinding.ActivityMainBinding
import com.example.umc_hackathon.databinding.FragmentFormListBrandBinding
import com.example.umc_hackathon.databinding.FragmentFormListMarketingBinding
import com.example.umc_hackathon.databinding.FragmentFormListSocialBinding

class FormListBrand : Fragment() {

    var modelList = ArrayList<MySurvey>()
    private var linearLayoutManager: RecyclerView.LayoutManager? = null
    private var recyclerAdapter: RecyclerView.Adapter<WaitingSurveyListRAdapter.MyViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentFormListBrandBinding.inflate(layoutInflater)

        // 리스트 생성
        for (i in 1..10){
            val mySurvey = MySurvey(title = "브랜드 $i")
            this.modelList.add(mySurvey)
        }

        val view = inflater!!.inflate(R.layout.fragment_form_list_brand, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_brand_rv)

        recyclerAdapter = WaitingSurveyListRAdapter(modelList)
        linearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerAdapter
        recyclerView.setHasFixedSize(true)

        return view
    }

}