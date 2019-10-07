package sourceset564.samples.mvvm.feature.imdbmovie

import androidx.fragment.app.Fragment
import id.nz.template.mvvm.core.BaseView
import id.nz.template.mvvm.core.extension.toast

/**
 * Created by Anwar on 10/7/2019.
 */
class MovieDashboardFragment : Fragment(), BaseView {

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showMessage(resId: Int) {
        toast(resources.getString(resId))
    }

    override fun onError(code: Int) {
        toast(code.toString())
    }

    override fun isNetworkConnected(): Boolean {
        return true
    }

    override fun onConnectionStatusChange() {

    }
}