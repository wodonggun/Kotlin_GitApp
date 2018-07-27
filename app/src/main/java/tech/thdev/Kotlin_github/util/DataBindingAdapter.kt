package tech.thdev.Kotlin_github.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import tech.thdev.Kotlin_github.R

@BindingAdapter("imageFromUrl")
fun imageFromUrl(imageView: ImageView, imageUrl: String) {
    imageView.loadUrl(imageUrl, R.drawable.ic_search)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = View.VISIBLE.takeIf { value } ?: View.GONE
}