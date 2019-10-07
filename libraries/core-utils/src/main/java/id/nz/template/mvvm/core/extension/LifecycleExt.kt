package id.nz.template.mvvm.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by Anwar on 10/7/2019.
 */

fun <T> LifecycleOwner.observe( observable : LiveData<T>, observer : (T) -> Unit){
    observable.observe(this, Observer {
        observer(it)
    })
}