package com.colan.kindercare.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ChildSelectionBroadCast : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if (childListener != null){
            childListener!!.onTriggerChildSelection()
        }
    }

    companion object {
        var childListener: ChildSelectionListener? = null
    }

}
