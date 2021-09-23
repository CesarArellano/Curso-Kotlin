package com.cesararellano.possessorapp

import java.util.*

data class ThingDataClass (
    var thingName: String = "",
    var pesosValue: Int = 0,
    val serialNumber: UUID = UUID.randomUUID(),
    val creationDate: Date = Date()

)