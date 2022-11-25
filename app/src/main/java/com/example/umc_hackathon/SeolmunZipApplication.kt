package com.example.umc_hackathon

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class SeolmunZipApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
    }
}