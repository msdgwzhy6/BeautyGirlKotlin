package com.moon.beautygirlkotlin.network.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author: moon
 * created on: 18/5/2 上午10:49
 * description:
 */
interface DouBanAPI {

    /**
     * 根据cid查询不同类型的妹子图片
     */
    @GET("/")
    suspend fun getDoubanMeizi(@Query("cid") cid: Int, @Query("page") pageNum: Int): Response<ResponseBody>

}