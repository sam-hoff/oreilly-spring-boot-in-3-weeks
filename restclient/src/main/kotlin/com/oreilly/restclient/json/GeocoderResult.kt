package com.oreilly.restclient.json

data class GeocoderResult(
    val formattedAddress: String,
    val geometry: GeocoderGeometry
)