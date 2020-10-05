package com.example.myapplication.data.remote.data

import com.google.gson.annotations.SerializedName

data class Errors(@SerializedName("msg")
                  var message: String)