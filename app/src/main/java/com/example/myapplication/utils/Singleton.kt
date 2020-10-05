package com.example.myapplication.utils

import android.content.IntentFilter
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R
import java.io.File


object Singleton {

    var isDashBoardFragmentVisible: Boolean=false
    var isNetworkConnected: Boolean = false
    var isInboxClicked:Boolean=false
    var isSentClicked:Boolean=false
    var isTrashClicked:Boolean=false
    var isBackPressFromMessage:Boolean=false
    var isNotiDelete:Boolean = false
    var isSelectedTypeBtn:String = ""

    //reports
    var reportIfSadmin:Boolean = false
    var getSelectedRadioBtn : String = ""
    var getSelectedRadioSection : String = ""
    var getSelectedRadioClass : String = ""

    var getSelectSalaryRadioBtn : String = ""
    //end reports//

    var sendToList=ArrayList<String>()
    var userListId=ArrayList<String>()
    var userListEmail=ArrayList<String>()
    var users=ArrayList<String>()
    var teachersId = ArrayList<String>()
    var studentsId = ArrayList<String>()
    var messaageUserSelected = ArrayList<String>()
    var mixedId = ArrayList<String>()
    var filePath = ArrayList<File?>()
    var profileFilePath: File ? = null
    var filePathString = ArrayList<String>()
    var fileSize = ArrayList<Float>()
    var messageAttachmentFileSize = ""
    //var videoPath = ArrayList<File?>()
    var teacherDOB :String? = ""
    var parentDashboardItems :String= ""
    var getParentActivityType :String= ""
    var getSchoolId : String = ""
    var attachmentPaths = ArrayList<String>()
    var attachmentPathsParent = ArrayList<String>()
    var attachmentPathsParentSize: Int = 0
    var TEACHER_ATTENDANCE_STATUS:String = ""
    var menuId = ""
    var classId = ""
    var classIdList = ArrayList<String>()
    var courseId = ArrayList<String>()
    var spinnerPosition = 0
    var notiMessageDelete = ""
    var reportSelectedFragment:String = ""
    var reportAttndanceSelectedUser:String = ""
    var reportSalarySelectedUser:String = ""
    var reportClassValue = ""
    var reportSectionValue = ""

    var attendanceListUnselect:Boolean = false

    var dailyActvityAdminEdit :String = ""

    var xlsSheetUrl: String = ""

    var PPName : String = ""
    var PPRelationship : String = ""
    var PPContactNo : String = ""

    //attendance
    var CURRENT_SELECTED_DATE : String = ""
    var enrollmentClass : String = ""
    var sectionSelected : String = ""
    var classSelected : String = ""

    @BindingAdapter("ImageBinding")
    @JvmStatic
    fun ImageBinding(image: ImageView, imageUrl: Int) {
        Glide.with(image.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)
    }

    @BindingAdapter("ImageBindingUrl")
    @JvmStatic
    fun ImageBindingUrl(image: ImageView, imageUrl: String) {
        Glide.with(image.context)
            .load(imageUrl)

            .into(image)
    }
    //.placeholder(R.drawable.loader_new)

    @BindingAdapter("VideoBindingUrl")
    @JvmStatic
    fun videoBindingUrl(video: VideoView, videoUrl: String) {
        val uri = Uri.parse(videoUrl)
        video.setVideoURI(uri)
        video.seekTo( 1 )
        video.pause()
        //video.start()
    }

    @BindingAdapter("layout_marginTop")
    @JvmStatic
    fun setMarginTop(view: View, margin: Int) {
        val params = view.layoutParams
        if (params is ViewGroup.MarginLayoutParams) {
            params.topMargin = margin
            view.requestLayout()
        }
    }

}