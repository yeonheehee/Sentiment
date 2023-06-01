package com.example.sentiment;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class kakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "e5a8a2bf8b9657267ccdfbfb49662821");
    }
}
