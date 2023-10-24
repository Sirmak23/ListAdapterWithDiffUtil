package com.example.listadapterwithdiffutil.ui.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(
    url: String? = "",
    shouldCircleCrop: Boolean = false
) {
    val requestBuilder = Glide.with(this.context).setDefaultRequestOptions(
        RequestOptions()
    ).load(url)

    if (shouldCircleCrop) {
        requestBuilder.circleCrop()
    }
    requestBuilder.into(this@loadFromUrl)
}
