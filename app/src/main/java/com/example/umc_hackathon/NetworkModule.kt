package com.example.umc_hackathon

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

const val BASE_URL = "http://seolmunzip.shop:9000" //추후 수정
//http://seolmunzip.shop:9000
//http://3.36.252.208:9000


fun getRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .hostnameVerifier(
        HostnameVerifier { hostname, session ->
            val hv = HttpsURLConnection.getDefaultHostnameVerifier()
            hv.verify("seolmunzip.shop", session)
        })
        .build()

    val gson: Gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
//        .client(unsafeOkHttpClient().build())
        .client(client)
        .build()

    return retrofit

}

//private fun unsafeOkHttpClient(): OkHttpClient.Builder {
//    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//        override fun checkClientTrusted(
//            chain: Array<out java.security.cert.X509Certificate>?,
//            authType: String?
//        ) {
//
//        }
//
//        override fun checkServerTrusted(
//            chain: Array<out java.security.cert.X509Certificate>?,
//            authType: String?
//        ) {
//
//        }
//
//        override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? {
//            return arrayOf()
//        }
//    })
//
//    val sslContext = SSLContext.getInstance("SSL")
//    sslContext.init(null, trustAllCerts, SecureRandom())
//
//    val sslSocketFactory = sslContext.socketFactory
//
//    val builder = OkHttpClient.Builder()
//    builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
//    builder.hostnameVerifier { hostname, session -> true }
//
//    return builder
//
//    if (retrofit2 == null) {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client: OkHttpClient =
//            Builder().addInterceptor(interceptor).hostnameVerifier(object : HostnameVerifier() {
//                fun verify(hostname: String?, session: SSLSession?): Boolean {
//                    val hv: HostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier()
//                    return hv.verify("api.skyscanner.net", session)
//                }
//            }).build()
//        retrofit2 = Retrofit.Builder()
//            .baseUrl(SKY_BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//}

