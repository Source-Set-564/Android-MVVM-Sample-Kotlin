package id.nz.template.mvvm.core

import androidx.annotation.IdRes
import androidx.annotation.StringRes

/**
 * Created by Anwar on 10/7/2019.
 */
interface BaseView {
    fun showMessage(message : String)
    fun showMessage(@IdRes @StringRes resId : Int)
    fun onError(code : Int)
    fun isNetworkConnected() : Boolean
    fun onConnectionStatusChange()
}