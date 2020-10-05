package com.colan.kindercare.bindiingUtils.module

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.colan.kindercare.bindiingUtils.implementaion.IImageViewBinding
import com.colan.kindercare.bindiingUtils.implementaion.IViewBinding
import com.example.myapplication.R


class BaseImageViewBinding : IImageViewBinding {

    override fun setLeaveImageResources(imageView: AppCompatImageView, fileType: String) {
        val requestOptions = RequestOptions().centerCrop()
        //var filePath: Int = R.drawable.ic_annual_leave
        var filePath: Int = 0
        when (fileType) {
            /*Constants.SICK_LEAVE -> filePath = R.drawable.ic_medical_leave
            Constants.CASUAL_LEAVE -> filePath = R.drawable.ic_annual_leave
            Constants.LOP -> filePath = R.drawable.ic_other_leave*/
            //else -> filePath = R.drawable.ic_other_leave
            //else -> filePath = R.drawable.ic_weekly_menu
        }
        Glide
            .with(imageView.context)
            .load(filePath)
            .apply(requestOptions)
            .into(imageView)
    }

    override fun setImageFromDrawable(imageView: ImageView, filePath: Int?) {
        Glide.with(imageView.context).load(filePath).into(imageView)
    }

    override fun setImageFromUrl(imageView: ImageView, filePath: String?) {
        val option: RequestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_launcher_background)
                .fitCenter()

        Glide.with(imageView.context)
                .load(filePath)
                .apply(option)
                .into(imageView)
    }

    override fun setUpForAttachent(imageView: ImageView, filePath: String?) {
        val extension: String = filePath?.substring(filePath.lastIndexOf(".") + 1)!!
        Log.d("extention",""+extension)
        val option: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_launcher_background)
            .fitCenter()

        if(extension=="png" || extension=="jpg" || extension=="jpeg"){
            Glide.with(imageView.context)
                .load(filePath)
                .apply(option)
                .into(imageView)
        } else if(extension=="mp4" || extension =="mkv"  || extension =="avi"  || extension =="m4v" || extension =="av1" ){
            val requestOptions: RequestOptions = RequestOptions()
            requestOptions.isMemoryCacheable()
            requestOptions.override(70, 40)
            Glide.with(imageView.context).setDefaultRequestOptions(requestOptions).load(filePath)
                .into(imageView)
        } else{
            /*var filePath: Int = R.drawable.ic_pdf
            when (extension) {
                "pdf" -> filePath = R.drawable.ic_pdf
                "docx" -> filePath = R.drawable.ic_doc
                "txt" -> filePath = R.drawable.ic_text
            }*/
            Glide
                .with(imageView.context)
                .load(filePath)
                .into(imageView)
        }

    }

    override fun thumbImageFromUrl(imageView: ImageView, filePath: String?) {
//        val option: RequestOptions = RequestOptions()
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//
//            .fitCenter()
//
//        Glide.with(imageView.context)
//            .load(filePath)
//            .apply(option)
//            .into(imageView)

        //val options = RequestOptions().frame(1000)
//        Glide.with(imageView.context).load(filePath).thumbnail(0.1f)
//            .into(imageView)

        val requestOptions: RequestOptions = RequestOptions()
        requestOptions.isMemoryCacheable()
        requestOptions.override(70, 40)
        Glide.with(imageView.context).setDefaultRequestOptions(requestOptions).load(filePath)
            .into(imageView)
    }

   /* override fun setImageFromUrlForAttach(imageView: ImageView, filePath: String?) {
        val option: RequestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.loader_transparent)
            .fitCenter()

        Glide.with(imageView.context)
            .load(filePath)
            .apply(option)
            .into(imageView)
    }*/

    override fun goneUnless(view: View, str: String?) {
        view.visibility = if (str !=null){

            View.VISIBLE
        } else{
            View.INVISIBLE
        }
    }

}

class BaseViewBinding : IViewBinding {
    override fun setVisibility(view: View, boolean: Boolean) {
        if (boolean) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    override fun setVisibility(view: View, int: Int?) {
        when(int) {
            0 ->{
                view.visibility = View.VISIBLE
            }
            1 ->{
                view.visibility = View.GONE
            }
            else ->{
                view.visibility = View.VISIBLE
            }

        }
    }

    override fun setTextColor(view: TextView, string: Int?) {
        when(string) {
            1 ->{
                view.setTextColor(Color.parseColor("#2ECC71"))
            }
            2 ->{
                view.setTextColor(Color.parseColor("#C72A1F"))
            }
            else ->{
                view.setTextColor(Color.parseColor("#F29C12"))
            }

        }
    }
}



