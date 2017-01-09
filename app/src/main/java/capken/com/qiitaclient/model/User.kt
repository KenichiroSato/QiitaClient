package capken.com.qiitaclient.model

import android.os.Parcel
import android.os.Parcelable

/**
* Created by ken on 2017/01/07..
*/

data class User(val id: String,
                val name: String,
                val profileImageUrl: String) : Parcelable {

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel) : User = source.run {
                User(readString(), readString(), readString())
            }

            override fun newArray(size: Int): Array<User?> = kotlin.arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.run {
            writeString(id)
            writeString(name)
            writeString(profileImageUrl)
        }
    }
}