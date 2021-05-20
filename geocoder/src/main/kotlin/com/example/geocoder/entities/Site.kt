package com.example.geocoder.entities

class Site(val address: String, val lat: Double?, val lng: Double?) {
    override fun toString(): String {
        return "Site: address=$address, latitude=$lat, long=$lng"
    }
}