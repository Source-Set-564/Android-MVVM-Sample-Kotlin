package id.nz.template.mvvm.core.extension

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Anwar on 10/7/2019.
 */

fun Activity.toast(message : String?){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun Fragment.toast(message: String?){
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun TextView.textOrGone(input : CharSequence?){
    input?.run {
        text = input
        visible()
    } ?: gone()
}

@BindingAdapter("app:fromUrl")
fun ImageView.fromUrl(url : String){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions())
        .into(this)
}

@BindingAdapter("app:fromUrlCircle")
fun ImageView.fromUrlCircle(url : String){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions()
            .transform(CircleCrop())
        )
        .into(this)
}

@BindingAdapter("app:fromUrl")
fun AppCompatImageView.fromUrl(url : String){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions())
        .into(this)
}

@BindingAdapter("app:fromUrlCircle")
fun AppCompatImageView.fromUrlCircle(url : String){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions()
            .transform(CircleCrop())
        )
        .into(this)
}