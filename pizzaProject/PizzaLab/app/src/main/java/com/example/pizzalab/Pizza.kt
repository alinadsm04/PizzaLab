package com.example.pizzalab

import android.os.Parcel
import android.os.Parcelable

data class Pizza(
    val imageResourceId: Int,
    val name: String,
    val description: String,
    val price: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageResourceId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pizza> {
        override fun createFromParcel(parcel: Parcel): Pizza {
            return Pizza(parcel)
        }

        override fun newArray(size: Int): Array<Pizza?> {
            return arrayOfNulls(size)
        }
    }
}
