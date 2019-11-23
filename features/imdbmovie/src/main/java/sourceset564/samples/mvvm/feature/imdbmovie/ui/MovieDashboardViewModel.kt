package sourceset564.samples.mvvm.feature.imdbmovie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.nz.app.mvppattern.data.model.Movie
import sourceset564.samples.mvvm.feature.imdbmovie.domain.MovieDashboardContract
import sourceset564.samples.mvvm.feature.imdbmovie.domain.MovieDashboardUseCase
import javax.inject.Inject

/**
 * Created by Anwar on 11/22/2019.
 */

class MovieDashboardViewModel @Inject constructor(
    private val useCase : MovieDashboardContract.UseCase) : ViewModel(), MovieDashboardContract.Contract{

    private val _loadPopular = MutableLiveData<Boolean>()
    val loadPopular : LiveData<Boolean>
        get() = _loadPopular

    val popularErrorMsg = MutableLiveData<String?>()

    private val _loadTopRated = MutableLiveData<Boolean>()
    val loadTopRated : LiveData<Boolean>
        get() = _loadTopRated

    val topRatedErrorMsg = MutableLiveData<String?>()

    private val _isRefresh = MutableLiveData<Boolean>()

    val isRefresh : LiveData<Boolean>
        get() = _isRefresh

    private val _listPopular = MutableLiveData<List<Movie>>()
    val listPopular : LiveData<List<Movie>>
        get() = _listPopular

    private val _listTopRated = MutableLiveData<List<Movie>>()
    val listTopRated : LiveData<List<Movie>>
        get() = _listTopRated

    init {
        useCase.setContract(this)
        _isRefresh.value = true
        getPopular()
        getTopRate()
    }

    fun getPopular(){
        _loadPopular.value = true
        popularErrorMsg.value = null
        useCase.getPopular(1)
    }

    fun getTopRate(){
        _loadTopRated.value = true
        topRatedErrorMsg.value = null
        useCase.getTopRate(1)
    }

    override fun onPopularFetched(list: List<Movie>, currentPage: Int, countPage: Int) {
        _loadPopular.value = false
        updateRefreshStatus()
        _listPopular.value = list
    }

    override fun onPopularError(throwable: Throwable) {
        _loadPopular.value = false
        updateRefreshStatus()
        popularErrorMsg.value = "Popular ${throwable.message}"
    }

    override fun onTopRateFetched(list: List<Movie>, currentPage: Int, countPage: Int) {
        _loadTopRated.value = false
        updateRefreshStatus()
        _listTopRated.value = list
    }

    override fun onTopRateError(throwable: Throwable) {
        _loadTopRated.value = false
        updateRefreshStatus()
        topRatedErrorMsg.value = "Top Rated ${throwable.message}"
    }

    private fun updateRefreshStatus(){
        if(_isRefresh.value ?: false) {
            val isLoadPopular = _loadPopular.value ?: false
            val isLoadTopRated = _loadTopRated.value ?: false
            _isRefresh.value = !(!isLoadPopular && !isLoadTopRated)
        }
    }

    fun refresh(){
        _isRefresh.value = true
        getPopular()
        getTopRate()
    }

    fun stop(){
        useCase.onStop()
    }

}