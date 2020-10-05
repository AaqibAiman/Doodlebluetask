package com.example.myapplication.data.remote.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UserProfileResponse {


    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("rank")
    @Expose
    var rank: String? = null

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("supply")
    @Expose
    var supply: String? = null

    @SerializedName("maxSupply")
    @Expose
    var maxSupply: String? = null

    @SerializedName("marketCapUsd")
    @Expose
    var marketCapUsd: String? = null

    @SerializedName("volumeUsd24Hr")
    @Expose
    var volumeUsd24Hr: String? = null

    @SerializedName("priceUsd")
    @Expose
    var priceUsd: String? = null

    @SerializedName("changePercent24Hr")
    @Expose
    var changePercent24Hr: String? = null

    @SerializedName("vwap24Hr")
    @Expose
    var vwap24Hr: String? = null
}