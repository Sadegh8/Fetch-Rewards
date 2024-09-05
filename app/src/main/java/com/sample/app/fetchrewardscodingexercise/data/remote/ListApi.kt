package com.sample.app.fetchrewardscodingexercise.data.remote

import com.sample.app.fetchrewardscodingexercise.data.remote.dto.Items
import retrofit2.http.GET

interface ListApi {
    @GET("hiring.json")
    suspend fun getList(): List<Items>
}
