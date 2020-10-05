package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

fun Intent.getData(key: String): String {
    return extras?.getString(key) ?: "intent is null"
}


const val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
private val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
//private val POSTCODE="^[1-9][0-9]{3}\$"
private val POSTCODE="^(0[289][0-9]{2})|([1-9][0-9]{3})\$"
private val MOBILE="((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}"
private val LEAVE_TYPE: String
    get() = listOfLeave.toString()

var listOfLeave: ArrayList<String?> = object : ArrayList<String?>() {
    init {
        add("1")
        add("2")
        add("3")
    }
}

private val RELATION_TYPE: String
    get() = relationshipArray.toString()

var ddd = arrayOf("Father", "Mother", "Uncle", "Aunty", "GrandFather", "GrandMother")
var relationshipArray =object :ArrayList<String?>() {
    init {
        addAll(ddd)
    }


}
private val SECTION_TYPE: String
    get() = listOfSection.toString()

var listOfSection: ArrayList<String?> = object : ArrayList<String?>() {
    init {
        add("A")
        add("B")
        add("C")
    }
}


fun validateEmail(email: String): Boolean {
    val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher: Matcher
    matcher = pattern.matcher(email)
    return matcher.matches()
}

fun isValidMobile(phone: String): Boolean {
    return if (!Pattern.matches("[a-zA-Z]+", phone)) {
        phone.length > 9 && phone.length <= 10
    } else false
}


fun validateMobile(mobile: String): Boolean {
    val pattern: Pattern = Pattern.compile(MOBILE)
    val matcher: Matcher
    matcher = pattern.matcher(mobile)
    return matcher.matches()
}

fun validateLeaveDropdown(leaveType: String): Boolean {
    val pattern: Pattern = Pattern.compile(LEAVE_TYPE)
    val matcher: Matcher
    matcher = pattern.matcher(leaveType)
    return matcher.matches()
}
fun validateSectionDropdown(leaveType: String): Boolean {
    val pattern: Pattern = Pattern.compile(SECTION_TYPE)
    val matcher: Matcher
    matcher = pattern.matcher(leaveType)
    return matcher.matches()
}

fun validateRelationDropdown(leaveType: String): Boolean {
    val pattern: Pattern = Pattern.compile(RELATION_TYPE)
    val matcher: Matcher
    matcher = pattern.matcher(leaveType)
    return matcher.matches()
}
fun validatePostalCode(code: String): Boolean {
    val pattern: Pattern = Pattern.compile(POSTCODE)
    val matcher: Matcher
    matcher = pattern.matcher(code)
    return matcher.matches()
}

fun compareDate(startTime: String, endTime: String): Boolean {
    val pattern = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    try {
        val time1: Date? = sdf.parse(startTime)
        val time2: Date? = sdf.parse(endTime)
        return time1?.before(time2)!!
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return false
}

fun compareDateNew(startTime: String, endTime: String): Boolean {
    val pattern = "dd-MM-yyyy"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    try {
        val time1: Date? = sdf.parse(startTime)
        val time2: Date? = sdf.parse(endTime)
        if (time1?.equals(time2)!!){
            return time1?.equals(time2)!!
        } else{
            return time1?.before(time2)!!
        }

    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return false
}

fun getUniqueImageFilename(): String {
    return "img_" + System.currentTimeMillis() + ".jpg"
}

fun createpicdirectory(): File {
    val root =
        File(Environment.getExternalStorageDirectory().toString() + File.separator + Constants.DIRECTORY_NAME + File.separator)
    root.mkdirs()
    return root

}


fun capitalize(line: String): String {
    return Character.toUpperCase(line[0]) + line.substring(1)
}
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.ShowSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun Activity.logd(message: String) {
    //if (BuildConfig.DEBUG)
    Log.d(this::class.java.simpleName, message)
}

fun getAge(year: Int, month: Int, day: Int): String {
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()
    dob.set(year, month, day)
    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    val ageInt = age
    return ageInt.toString()
}

var bundle = Bundle()

fun navigateTo(context: Context, clazz: Class<*>, mExtras: Bundle?) {
    val intent = Intent(context, clazz)
    if (mExtras != null) {
        intent.putExtras(mExtras)
    }
    context.startActivity(intent)
}

fun removeLast(s: String,  n:Int):String {
    var result = ""
    if (s.isNotEmpty()) {
        result = s.substring(0, s.length - n);
    }
    return result;
}

fun navigateWithResult(context: Activity, clazz: Class<*>, mExtras: Bundle?) {
    val intent = Intent(context, clazz)
    if (mExtras != null) {
        intent.putExtras(mExtras)
    }
    context.startActivityForResult(intent, 101)
}

fun navigateWithClearTop(context: Context, clazz: Class<*>, mExtras: Bundle) {
    val intent = Intent(context, clazz)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    intent.putExtras(mExtras)
    context.startActivity(intent)
    ActivityCompat.finishAffinity(context as Activity)
}


fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

/**
 * Get next date from current selected date
 *
 * @param date date
 */
fun incrementDateByOne(date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    c.add(Calendar.DATE, 1)
    return c.time
}

fun incrementMonthByOne(date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    val month = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
    c.add(Calendar.MONTH, 1)
    return c.time
}

/**
 * Get previous date from current selected date
 *
 * @param date date
 */
fun decrementDateByOne(date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    c.add(Calendar.DATE, -1)
    return c.time
}

fun decrementMonthByOne(date: Date): Date {
    val c = Calendar.getInstance()
    c.time = date
    val monthStartDate = Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH)
    c.add(Calendar.MONTH, -1)
    /*val monthEndDate = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
    c.add(monthEndDate, 1)*/
    return c.time
}

fun dateToStringCustom(dateString: String):String{
    val src = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val dest = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dateToStringCustomDOB(dateString: String):String{
    val src = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dest = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

/*fun dateToStringCustomAPIs(dateString: String?):String{
    val src = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val dest = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date!!)
}*/

fun dateToStringCustomAPIs(dateString: String?):String?{
    if(dateString!=""){
        val src = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val dest = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var date: Date? = null
        try {
            date = src.parse(dateString)
        } catch (e: ParseException) {
            Log.d("Exception", ""+e.message)
        }
        return dest.format(date!!)
    }
    else{
        return ""
    }
}


fun StringToDateConvert(dateString:String):Date{
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    var convertedDate: Date? = Date()
    try {
        convertedDate = dateFormat.parse(dateString)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
return convertedDate!!
}

fun stringToDateConvert1(dateString:String):Date{
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    var convertedDate: Date? = Date()
    try {
        convertedDate = dateFormat.parse(dateString)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    return convertedDate!!
}
fun stringToDateforParent(dateString:String):Date{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var convertedDate: Date? = Date()
    try {
        convertedDate = dateFormat.parse(dateString)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    return convertedDate!!
}

fun StringToMonthConvert(dateString:String):Date{
    val dateFormat = SimpleDateFormat("dd MMMM yyyy")
    var convertedDate: Date? = Date()
    try {
        convertedDate = dateFormat.parse(dateString)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
    }
    return convertedDate!!
}


fun dateCustom(dateString: String,pattern:String):String{
    val src = SimpleDateFormat(pattern, Locale.getDefault())
    val dest = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date)
}


fun dateToTimeStringCustom(dateString: String):String{
    val src = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    val dest = SimpleDateFormat("hh:mm a", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dateToTimeStringCustom2(dateString: String):String{
    val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val dest = SimpleDateFormat("hh:mm a dd", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dateToTimeStringCustom3(dateString: String):String{
    val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val dest = SimpleDateFormat("hh:mm a \ndd:MM:yyyy", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dateToTimeEditDailyactivty(dateString: String):String{
    val src = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val dest = SimpleDateFormat("hh:mm", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}


fun dateandtimeToDateStringCustom(dateString: String):String{
    val src = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    val dest = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dateandtimeInDailyActivityList(dateString: String):String{
    val src = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    val dest = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dateToFromDateStringCustom(dateString: String):String{
    //val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val src = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    val dest = SimpleDateFormat("dd MMM", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}
fun dateYearDateStringCustom(dateString: String):String{
    //val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val src = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    val dest = SimpleDateFormat("yyyy", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}
fun dateFromToStringCustom(dateString: String):String{
    //val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val src = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
    val dest = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date)
}

fun dateIntFromDateStringCustom(dateString: String):String{
    //val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val src = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dest = SimpleDateFormat("d", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}

fun dayThreeFromFourStringCustom(dateString: String):String{
    //val src = SimpleDateFormat("hh:mm a dd MMM yyyy", Locale.getDefault())
    val src = SimpleDateFormat("EEEE", Locale.getDefault())
    val dest = SimpleDateFormat("E", Locale.getDefault())
    var date: Date? = null
    try {
        date = src.parse(dateString)
    } catch (e: ParseException) {
        Log.d("Exception", ""+e.message)
    }

    return dest.format(date);
}


fun fullDateToDateAndMonth(dateString: String):String{
    //1995-03-28
    val src = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dest =SimpleDateFormat("MM-dd",Locale.getDefault())
    var date: Date? = null
    try{
        date = src.parse(dateString)
    }catch (e:ParseException){
        Log.d("Exception",""+e.message)
    }
    return dest.format(date!!)
}

fun fullAppliedDateMonth(dateString: String):String{
    //1995-03-28
    val src = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dest =SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
    var date: Date? = null
    try{
        date = src.parse(dateString)
    }catch (e:ParseException){
        Log.d("Exception",""+e.message)
    }
    return dest.format(date!!)
}

fun getRealPathFromURI(context:Context,contentURI: Uri): String {
    val result: String
    val cursor = context.getContentResolver().query(contentURI, null, null, null, null)
    if (cursor == null) { // Source is Dropbox or other similar local file path
        result = contentURI.getPath()!!
    } else {
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        result = cursor.getString(idx)
        cursor.close()
    }
    return result
}

fun getMonth(month: Int): String {
    return DateFormatSymbols().getMonths()[month - 1]
}

fun getClass(data:Int):String{
    var className=""
    when(data){

        1->{
            className="LKG"
        }
        2->{
            className="UKG"
        }
        3->{
            className="PRE KG"
        }
    }
    return  className
}


fun getPaymentType(data:Int):String{
    var paymentType=""
    when(data){

        0->{
            paymentType="Partially"
        }
        1->{
            paymentType="Full"
        }

    }
    return  paymentType
}


fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

internal fun Context.getDrawableCompat(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

internal fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))



