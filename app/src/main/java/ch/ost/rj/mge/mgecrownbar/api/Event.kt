package ch.ost.rj.mge.mgecrownbar.api

import android.os.Parcel
import android.os.Parcelable

// Needs to be Parcelable to be transferable between Activities

data class Event (
    val eventDate: String?,
    val eventName: String?
) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(eventDate)
        parcel.writeString(eventName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}