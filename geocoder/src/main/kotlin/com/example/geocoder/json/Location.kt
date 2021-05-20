package com.example.geocoder.json

class Location {
    public var lat: Double? = null
    public val lng: Double? = null

    override fun toString(): String {
        return "($lat, $lng)"
    }
}