package com.oreilly.restclient.json

data class GeocoderResponse(val results: List<GeocoderResult>, val status: String){
    fun getLocation(): GeocoderLocation {
        return results[0].geometry.location
    }
    fun getFormattedAddress(): String {
        return results[0].formattedAddress
    }
}