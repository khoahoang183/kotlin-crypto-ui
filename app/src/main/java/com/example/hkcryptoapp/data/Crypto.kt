package com.example.hkcryptoapp.data

import java.io.Serializable

data class Crypto(
    var name: String = "",
    var volume: Float = 0F, // million
    var price: Float = 0F,
    var x: Int = 0,
    var profit: Float = 0F
):Serializable