package com.portal.githubservices.utilities.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImageWithUrl(url: String?) {
    url?.let {
        Glide.with(context).load(it).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
    }
}

fun ImageView.loadImageWithUrlAndPlaceHolder(url: String?, placeHolder: Int) {
    url?.let {
        Glide.with(context).load(it).placeholder(placeHolder).into(this)
    }
}

