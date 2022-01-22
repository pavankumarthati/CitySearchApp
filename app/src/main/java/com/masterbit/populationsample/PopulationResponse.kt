package com.masterbit.populationsample

data class PopulationResponse(
    val error: Boolean,
    val msg: String,
    val data: List<CityItem>
)

data class CityItem(
    val city: String,
    val country: String,
)