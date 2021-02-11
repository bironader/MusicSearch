package com.bironader.musicsearch.framework.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object GlideUtils {


    @BindingAdapter(value = ["imageUrl"], requireAll = false)
    @JvmStatic
    fun loadImageUrl(imageView: ImageView, imageUrI: String?) {
        val requestOptions =
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(RoundedCorners(16))

        Glide.with(imageView)
            .load(imageUrI)
            .apply(requestOptions)
            .transform(RoundedCorners(16))
//            .error(R.drawable.ic_broken_image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }


}