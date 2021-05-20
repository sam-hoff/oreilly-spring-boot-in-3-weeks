package com.example.geocoder.json

class Response {
    var results: MutableList<Result> = mutableListOf()
    var status: String = ""

    fun getLocation(): Location? {
        return results[0].geometry?.location
    }

    fun getFormattedAddress(): String {
        return results[0].formattedAddress
    }
}