package uae.finance.shrine_k.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    val id: String,
    val title: String,
    val type: String,
    val currency: String,
    val active: Boolean,
    val repeatable: Boolean,
    val repeatableDate: Int
) : Parcelable