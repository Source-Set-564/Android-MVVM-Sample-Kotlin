package id.nz.template.mvvm.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import id.nz.template.mvvm.core.extension.toast

/**
 * Created by Anwar on 11/22/2019.
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment(), BaseView {

    abstract fun contentView() : Int
    abstract fun doInjection()
    abstract fun onViewCreated()

    protected lateinit var binding : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInjection()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<T>(inflater,contentView(),container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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