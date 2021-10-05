package com.cesararellano.possessorapp

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class ThingDataClass(): Parcelable {
    var thingName: String = ""
    var pesosValue: Int = 0
    var serialNumber: UUID = UUID.randomUUID()
    var creationDate: Date = Date()

    // Deserealización
    constructor(parcel: Parcel) : this() {
        thingName = parcel.readString().toString()
        pesosValue = parcel.readInt()
        serialNumber = parcel.readSerializable() as UUID
        creationDate = parcel.readSerializable() as Date
    }

    // Serializar
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(thingName)
        dest.writeInt(pesosValue)
        dest.writeSerializable(serialNumber)
        dest.writeSerializable(creationDate)
    }

    companion object CREATOR : Parcelable.Creator<ThingDataClass> {
        override fun createFromParcel(source: Parcel): ThingDataClass {
            return ThingDataClass(source)
        }

        override fun newArray(size: Int): Array<ThingDataClass?> {
            return arrayOfNulls(size)
        }
    }
}