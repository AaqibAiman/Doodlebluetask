package com.colan.kindercare.data.model.weeklymenu

class MenuByDateModel {

    var date:String?=null
    var courseType: String? = ""
    var value: String? = ""

    constructor(date: String?) {
        this.date = date
    }

    constructor(courseType: String?, value: String?) {
        this.courseType = courseType
        this.value = value
    }


}