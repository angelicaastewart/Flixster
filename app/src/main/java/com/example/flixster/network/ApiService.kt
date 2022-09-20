package com.example.flixster.network

import com.example.flixster.models.Property
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://simple-node-app-nkd.herokuapp.com/"
//private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
private val retrofit = Retrofit.Builder()
    .baseUrl(ApiService.BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    )
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

private val api = retrofit.create(ApiService::class.java)
private val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
//private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ApiService{
    @GET(".")
    fun getAllData(): Call<List<Property>>

    companion object {

        //const val AUTH_HEADER = "x-api-key"
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }

    @GET("now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US&page=1")
    fun getData(@Query("limit") limit: Int = 5): Call<List<Property>>

}

private var listMyData = Types.newParameterizedType(List::class.java, Property::class.java)
private val adapter: JsonAdapter<List<Property>> = Moshi.Builder().build().adapter(listMyData)

}


