package com.example.mycoindiary

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDexApplication
import com.kakao.sdk.common.KakaoSdk


class GlobalApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        //카카오 sdk 초기화
        KakaoSdk.init(this,"f62c55422128e714426ef431300cb28f")
    }
}