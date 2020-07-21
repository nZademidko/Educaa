package com.example.englishwords.ui.retrofit.URLs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class RetroClient{

    companion object{

        private const val SMS_CODE_URL = "Ссылка"
    }

    private fun getRetrofitInstance(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(SMS_CODE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    open fun getApiService: ApiService(){
        return getRetrofitInstance().create(ApiService.class)
    }
}