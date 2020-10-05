package com.colan.kindercare.bindiingUtils.implementaion

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

interface IImageViewBinding {

    @BindingAdapter("customImageFromDrawable")
    fun setImageFromDrawable(imageView: ImageView, filePath: Int?)

    @BindingAdapter("customImageFromUrl")
    fun setImageFromUrl(imageView: ImageView, filePath: String?)

    @BindingAdapter("setUpForAttachent")
    fun setUpForAttachent(imageView: ImageView, filePath: String?)

    @BindingAdapter("thumbImageFromUrl")
    fun thumbImageFromUrl(imageView: ImageView, filePath: String?)
    /*@BindingAdapter("customImageFromUrl")
    fun setImageFromUrlForAttach(imageView: ImageView, filePath: String?)*/

    @BindingAdapter("goneUnless")
    fun goneUnless(view: View, str: String?)

    @BindingAdapter("setLeaveImageResources")
    fun setLeaveImageResources(imageView: AppCompatImageView, fileType: String)

}

interface IViewBinding {
    @BindingAdapter("isVisible")
    fun setVisibility(view : View, boolean: Boolean)

    @BindingAdapter("isVisible")
    fun setVisibility(view : View, int: Int?)

    @BindingAdapter("textColorCustom")
    fun setTextColor(view : TextView, string: Int?)

}