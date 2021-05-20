package com.example.geocoder.json

import com.fasterxml.jackson.annotation.JsonProperty

class Result {
    @JsonProperty("formatted_address")
    var formattedAddress: String = ""
    var geometry: Geometry? = null
}