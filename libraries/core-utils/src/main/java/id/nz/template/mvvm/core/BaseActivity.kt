package id.nz.template.mvvm.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import id.nz.template.mvvm.core.extension.toast

/**
 * Created by Anwar on 11/22/2019.
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), BaseView {

    abstract fun contentView() : Int
    abstract fun doInjection()
    abstract fun onViewCreated()

    protected var savedInstanceState : Bundle? = null

    protected lateinit var binding : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.savedInstanceState = savedInstanceState
        binding = DataBindingUtil.setContentView(this,contentView())
        binding.lifecycleOwner = this
        doInjection()
        onViewCreated()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showMessage(resId: Int) {
        showMessage(resources.getString(resId))
    }

    override fun isNetworkConnected(): Boolean {
        return true
    }
}