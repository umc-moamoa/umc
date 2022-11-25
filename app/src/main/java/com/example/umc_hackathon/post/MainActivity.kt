package com.example.umc_hackathon.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import com.example.umc_hackathon.auth.AuthActivity
import com.example.umc_hackathon.my.MyPageActivity
import com.example.umc_hackathon.databinding.ActivityMainBinding
import com.example.umc_hackathon.post.detail.PostDetailActivity
import com.example.umc_hackathon.post.list.FormListActivity
import com.example.umc_hackathon.post.list.PostListResponse

class MainActivity : AppCompatActivity(), WaitingSurveyView, PopularSurveyView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이벤트 리스너
        binding.mainProfileIv.setOnClickListener {
            if (getAccessToken().isNullOrBlank()) {
                val intent = Intent(this, AuthActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            } else {
                Log.d("리프레시 토큰있음", "마이페이지로~")
                val intent = Intent(this, MyPageActivity::class.java)
                intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }
        }

        binding.mainPopularSurveyLl.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.mainWaitingSurveyListLl.setOnClickListener {
            val intent = Intent(this, FormListActivity::class.java)
            intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // 스크롤뷰 시작 위치 상단으로 고정하기
        binding.mainSv.post {
            binding.mainSv.fullScroll(ScrollView.FOCUS_UP)
        }

        // 회원 구별 & 웰컴 메시지
        if (getNickName() == "" || getNickName() == null) {
            binding.mainWelcomeTv.text = "로그인이 필요합니다"
        } else {
            binding.mainWelcomeTv.text = getNickName() + "님! 반갑습니다"
        }

        // 인기있는 설문조사 조회
        getPopularSurvey()

        // 참여를 기다리는 설문조사 조회
        getWaitingSurvey()

    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("jwt", "")
    }

    private fun getAccessToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("accessToken", "")
    }

    private fun getRefreshToken(): String? {
        val spf = this.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getString("refreshToken", "")
    }

    private fun getNickName(): String? {
        val spf = this.getSharedPreferences("nickName", AppCompatActivity.MODE_PRIVATE)
        Log.d("nickName: ", "" + spf!!.getString("nickName", ""))
        return spf!!.getString("nickName", "")
    }

    private fun getPopularSurvey() {
        val postService = PostService()
        postService.setPopularSurveyView(this)
        postService.getPopularSurvey()
    }

    private fun getWaitingSurvey() {
        val postService = PostService()
        postService.setWaitingSurveyView(this)
        postService.getWaitingSurvey()
    }

    override fun onGetPopularSurveySuccess(postList: PostListResponse) {
        // 무지성 코드 죄송^^ 나중에 수정하겠음

        if(postList.result.isEmpty()) {
            Log.d("인기있는 설문조사: 첫번째" , "null")
            binding.mainPopularFirstTitleTv.text = "설문조사 개수 부족"
            binding.mainPopularFirstCountTv.text = "설문조사 개수 부족"
            binding.mainPopularFirstDeadlineTv.text = "설문조사 개수 부족"
            binding.mainPopularFirstPointTv.visibility = View.GONE

            binding.mainSecondTitleTv.text = "설문조사 개수 부족"
            binding.mainSecondCountTv.text = "설문조사 개수 부족"
            binding.mainSecondDeadlineTv.text = "설문조사 개수 부족"
            binding.mainSecondPointTv.visibility = View.GONE

            binding.mainThirdTitleTv.text = "설문조사 개수 부족"
            binding.mainThirdCountTv.text = "설문조사 개수 부족"
            binding.mainThirdDeadlineTv.text = "설문조사 개수 부족"
            binding.mainThirdPointTv.visibility = View.GONE
        }

        if(postList.result.size == 1) {
            binding.mainPopularFirstTitleTv.text = postList.result[0].title
            binding.mainPopularFirstCountTv.text = postList.result[0].qcount.toString() + "개의 항목"
            if(postList.result[0].dday == 0) {
                binding.mainPopularFirstDeadlineTv.text = "D - DAY"
            } else {
                binding.mainPopularFirstDeadlineTv.text = "D - " + postList.result[0].dday.toString()
            }
            binding.mainPopularFirstPointTv.text = postList.result[0].point.toString() + "P"

            binding.mainPopularFirstFolderView.setOnClickListener {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[0].postId)
                Log.d("list_item_post_id", postList.result[0].postId.toString());
                startActivity(intent)
                finish()
            }

            binding.mainSecondTitleTv.text = "설문조사 개수 부족"
            binding.mainSecondCountTv.text = "설문조사 개수 부족"
            binding.mainSecondDeadlineTv.text = "설문조사 개수 부족"
            binding.mainSecondPointTv.visibility = View.GONE

            binding.mainThirdTitleTv.text = "설문조사 개수 부족"
            binding.mainThirdCountTv.text = "설문조사 개수 부족"
            binding.mainThirdDeadlineTv.text = "설문조사 개수 부족"
            binding.mainThirdPointTv.visibility = View.GONE
        }

        if(postList.result.size == 2) {
            binding.mainPopularFirstTitleTv.text = postList.result[0].title
            binding.mainPopularFirstCountTv.text = postList.result[0].qcount.toString() + "개의 항목"
            if(postList.result[0].dday == 0) {
                binding.mainPopularFirstDeadlineTv.text = "D - DAY"
            } else {
                binding.mainPopularFirstDeadlineTv.text = "D - " + postList.result[0].dday.toString()
            }
            binding.mainPopularFirstPointTv.text = postList.result[0].point.toString() + "P"

            binding.mainPopularFirstFolderView.setOnClickListener {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[0].postId)
                Log.d("list_item_post_id", postList.result[0].postId.toString());
                startActivity(intent)
                finish()
            }

            binding.mainSecondTitleTv.text = postList.result[1].title
            binding.mainSecondCountTv.text = postList.result[1].qcount.toString() + "개의 항목"
            if(postList.result[1].dday == 0) {
                binding.mainSecondDeadlineTv.text = "D - DAY"
            } else {
                binding.mainSecondDeadlineTv.text = "D - " + postList.result[1].dday.toString()
            }
            binding.mainSecondPointTv.text = postList.result[1].point.toString() + "P"
            binding.mainSecondFolderView.setOnClickListener {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[1].postId)
                Log.d("list_item_post_id", postList.result[1].postId.toString());
                startActivity(intent)
                finish()
            }

            binding.mainThirdTitleTv.text = "설문조사 개수 부족"
            binding.mainThirdCountTv.text = "설문조사 개수 부족"
            binding.mainThirdDeadlineTv.text = "설문조사 개수 부족"
            binding.mainThirdPointTv.visibility = View.GONE
        }

        if(postList.result.size == 3) {
            binding.mainPopularFirstTitleTv.text = postList.result[0].title
            binding.mainPopularFirstCountTv.text = postList.result[0].qcount.toString() + "개의 항목"
            if(postList.result[0].dday == 0) {
                binding.mainPopularFirstDeadlineTv.text = "D - DAY"
            } else {
                binding.mainPopularFirstDeadlineTv.text = "D - " + postList.result[0].dday.toString()
            }
            binding.mainPopularFirstPointTv.text = postList.result[0].point.toString() + "P"

            binding.mainPopularFirstFolderView.setOnClickListener {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[0].postId)
                Log.d("list_item_post_id", postList.result[0].postId.toString());
                startActivity(intent)
                finish()
            }

            binding.mainSecondTitleTv.text = postList.result[1].title
            binding.mainSecondCountTv.text = postList.result[1].qcount.toString() + "개의 항목"
            if(postList.result[1].dday == 0) {
                binding.mainSecondDeadlineTv.text = "D - DAY"
            } else {
                binding.mainSecondDeadlineTv.text = "D - " + postList.result[1].dday.toString()
            }
            binding.mainSecondPointTv.text = postList.result[1].point.toString() + "P"
            binding.mainSecondFolderView.setOnClickListener {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[1].postId)
                Log.d("list_item_post_id", postList.result[1].postId.toString());
                startActivity(intent)
                finish()
            }

            binding.mainThirdTitleTv.text = postList.result[2].title
            binding.mainThirdCountTv.text = postList.result[2].qcount.toString() + "개의 항목"
            if(postList.result[2].dday == 0) {
                binding.mainThirdDeadlineTv.text = "D - DAY"
            } else {
                binding.mainThirdDeadlineTv.text = "D - " + postList.result[2].dday.toString()
            }
            binding.mainThirdPointTv.text = postList.result[2].point.toString() + "P"
            binding.mainThirdFolderView.setOnClickListener {
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[2].postId)
                Log.d("list_item_post_id", postList.result[2].postId.toString());
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onGetPopularSurveyFailure() {
        Log.d("인기있는 설문조사 / ", "MainActivity, 인기있는 설문조사 폼 목록을 불러오는데 실패했습니다")
    }

    override fun onGetWaitingSurveySuccess(postList: PostListResponse) {
//        Log.d("테스트", postList.result[0].title)

        if(postList.result.size == 0) {
            binding.mainWaitingSurveyItemTitleTv1.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemCountTv1.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemDeadlineTv1.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemPointTv1.visibility = View.GONE

            binding.mainWaitingSurveyItemTitleTv2.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemCountTv2.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemDeadlineTv2.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemPointTv2.visibility = View.GONE

            binding.mainWaitingSurveyItemTitleTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemCountTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemDeadlineTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemPointTv3.visibility = View.GONE
        }

        if(postList.result.size == 1) {
            binding.mainWaitingSurveyItemTitleTv1.text = postList.result[0].title
            binding.mainWaitingSurveyItemCountTv1.text = postList.result[0].qcount.toString() + "개의 항목"
            if(postList.result[0].dday == 0) {
                binding.mainWaitingSurveyItemDeadlineTv1.text = "D - DAY"
            } else {
                binding.mainWaitingSurveyItemDeadlineTv1.text = "D - " + postList.result[0].dday.toString()
            }
            binding.mainWaitingSurveyItemPointTv1.text = postList.result[0].point.toString() + "P"

            // 상세 페이지로 intent
            binding.mainWaitingSurveyItemLl1.setOnClickListener {
//          val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[0].postId)
                Log.d("list_item_my_survey_id", postList.result[0].postId.toString());
                startActivity(intent)
            }

            binding.mainWaitingSurveyItemTitleTv2.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemCountTv2.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemDeadlineTv2.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemPointTv2.visibility = View.GONE

            binding.mainWaitingSurveyItemTitleTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemCountTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemDeadlineTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemPointTv3.visibility = View.GONE
        }

        if(postList.result.size == 2) {
            binding.mainWaitingSurveyItemTitleTv1.text = postList.result[0].title
            binding.mainWaitingSurveyItemCountTv1.text = postList.result[0].qcount.toString() + "개의 항목"
            if(postList.result[0].dday == 0) {
                binding.mainWaitingSurveyItemDeadlineTv1.text = "D - DAY"
            } else {
                binding.mainWaitingSurveyItemDeadlineTv1.text = "D - " + postList.result[0].dday.toString()
            }
            binding.mainWaitingSurveyItemPointTv1.text = postList.result[0].point.toString() + "P"

            // 상세 페이지로 intent
            binding.mainWaitingSurveyItemLl1.setOnClickListener {
//          val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[0].postId)
                Log.d("list_item_my_survey_id", postList.result[0].postId.toString());
                startActivity(intent)
            }

            binding.mainWaitingSurveyItemTitleTv2.text = postList.result[1].title
            binding.mainWaitingSurveyItemCountTv2.text = postList.result[1].qcount.toString() + "개의 항목"
            if(postList.result[1].dday == 0) {
                binding.mainWaitingSurveyItemDeadlineTv2.text = "D - DAY"
            } else {
                binding.mainWaitingSurveyItemDeadlineTv2.text = "D - " + postList.result[1].dday.toString()
            }
            binding.mainWaitingSurveyItemPointTv2.text = postList.result[1].point.toString() + "P"

            // 상세 페이지로 intent
            binding.mainWaitingSurveyItemLl2.setOnClickListener {
//          val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[1].postId)
                Log.d("list_item_my_survey_id", postList.result[1].postId.toString());
                startActivity(intent)
            }

            binding.mainWaitingSurveyItemTitleTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemCountTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemDeadlineTv3.text = "설문조사 개수 부족"
            binding.mainWaitingSurveyItemPointTv3.visibility = View.GONE
        }

        if(postList.result.size == 3) {
            binding.mainWaitingSurveyItemTitleTv1.text = postList.result[0].title
            binding.mainWaitingSurveyItemCountTv1.text = postList.result[0].qcount.toString() + "개의 항목"
            if(postList.result[0].dday == 0) {
                binding.mainWaitingSurveyItemDeadlineTv1.text = "D - DAY"
            } else {
                binding.mainWaitingSurveyItemDeadlineTv1.text = "D - " + postList.result[0].dday.toString()
            }
            binding.mainWaitingSurveyItemPointTv1.text = postList.result[0].point.toString() + "P"

            // 상세 페이지로 intent
            binding.mainWaitingSurveyItemLl1.setOnClickListener {
//          val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[0].postId)
                Log.d("list_item_my_survey_id", postList.result[0].postId.toString());
                startActivity(intent)
            }

            binding.mainWaitingSurveyItemTitleTv2.text = postList.result[1].title
            binding.mainWaitingSurveyItemCountTv2.text = postList.result[1].qcount.toString() + "개의 항목"
            if(postList.result[1].dday == 0) {
                binding.mainWaitingSurveyItemDeadlineTv2.text = "D - DAY"
            } else {
                binding.mainWaitingSurveyItemDeadlineTv2.text = "D - " + postList.result[1].dday.toString()
            }
            binding.mainWaitingSurveyItemPointTv2.text = postList.result[1].point.toString() + "P"

            // 상세 페이지로 intent
            binding.mainWaitingSurveyItemLl2.setOnClickListener {
//          val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[1].postId)
                Log.d("list_item_my_survey_id", postList.result[1].postId.toString());
                startActivity(intent)
            }

            binding.mainWaitingSurveyItemTitleTv3.text = postList.result[2].title
            binding.mainWaitingSurveyItemCountTv3.text = postList.result[2].qcount.toString() + "개의 항목"
            if(postList.result[2].dday == 0) {
                binding.mainWaitingSurveyItemDeadlineTv3.text = "D - DAY"
            } else {
                binding.mainWaitingSurveyItemDeadlineTv3.text = "D - " + postList.result[2].dday.toString()
            }
            binding.mainWaitingSurveyItemPointTv3.text = postList.result[2].point.toString() + "P"

            // 상세 페이지로 intent
            binding.mainWaitingSurveyItemLl3.setOnClickListener {
//          val currentPosition: Int = postList[adapterPosition].postId //수정!!!!!! postId로 받아오기
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("list_item_post_id", postList.result[2].postId)
                Log.d("list_item_my_survey_id", postList.result[2].postId.toString());
                startActivity(intent)
            }
        }

        Log.d("참여를 기다리는 설문조사 / ", "MainActivity, 참여를 기다리는 설문조사 폼 목록을 불러오는데 성공했습니다")
    }

    override fun onGetWaitingSurveyFailure() {
        Log.d("참여를 기다리는 설문조사 / ", "MainActivity, 참여를 기다리는 설문조사 폼 목록을 불러오는데 실패했습니다")
    }
}