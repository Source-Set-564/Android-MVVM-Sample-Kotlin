package id.nz.template.mvvm.core.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Anwar on 10/7/2019.
 */

fun Any.jsonify() : String{
    return Gson().toJson(this)
}

fun <T> makeFromJson(json : String, refered : Class<T>) : T{
    return Gson().fromJson(json,refered)
}