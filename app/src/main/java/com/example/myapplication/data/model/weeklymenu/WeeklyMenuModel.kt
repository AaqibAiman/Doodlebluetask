package com.colan.kindercare.data.model.weeklymenu

class WeeklyMenuModel {
    var parentSpinnerIndex: Int? = 0
    var parentIndex: Int? = 0
    var courseType: String? = ""
    var mealsListIndex: String? = ""
    var parentSpinner: ArrayList<String>? = ArrayList()
    var weeklyChildMenuModel: ArrayList<WeeklyChildMenuModel> = ArrayList()


    inner class ChildSpinnerModel{
        var id: Int? = 0
        var value: String? = ""
    }

    inner class WeeklyChildMenuModel {
        var foodType: String? = ""
        var childSpinnerIndex = 0
        var id = 0
        var buttonVisibility: Boolean = true
        var childSpinner: ArrayList<String>? = ArrayList()
        var childSpinnerModel: ArrayList<ChildSpinnerModel>? = ArrayList()
        override fun toString(): String {
            return "WeeklyChildMenuModel(foodType=$foodType, childSpinnerIndex=$childSpinnerIndex, childSpinner=$childSpinner)"
        }
    }

    override fun toString(): String {
        return "WeeklyMenuModel(parentSpinnerIndex=$parentSpinnerIndex, parentIndex=$parentIndex, courseType=$courseType, parentSpinner=$parentSpinner, weeklyChildMenuModel=$weeklyChildMenuModel)"
    }

}