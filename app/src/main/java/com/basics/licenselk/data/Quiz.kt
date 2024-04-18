package com.basics.licenselk.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Quiz(
    @DrawableRes val image: Int,
    @StringRes val paper: Int)
 : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeInt(paper)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Quiz> {
        override fun createFromParcel(parcel: Parcel): Quiz {
            return Quiz(parcel)
        }

        override fun newArray(size: Int): Array<Quiz?> {
            return arrayOfNulls(size)
        }
    }
}

data class QuizItems(
    @StringRes val Question : String,
    @DrawableRes val Image : Int,
    val OptionList: List<String>,
    val CorrectOption : String
)