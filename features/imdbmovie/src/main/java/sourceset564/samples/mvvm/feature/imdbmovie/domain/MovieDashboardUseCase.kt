package sourceset564.samples.mvvm.feature.imdbmovie.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import sourceset564.samples.mvvm.feature.imdbmovie.data.source.MovieDataSource
import javax.inject.Inject

/**
 * Created by Anwar on 11/22/2019.
 */
class MovieDashboardUseCase @Inject constructor(private val dataSource: MovieDataSource)
    : MovieDashboardContract.UseCase {

    private lateinit var mContract : MovieDashboardContract.Contract
    private val disposable = CompositeDisposable()

    override fun getPopular(page : Int) {
        dataSource.getPopulars(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mContract.onPopularFetched(it.results,it.currentPage,it.totalPage)
            },{
                mContract.onPopularError(it)
            }).also {
                disposable.add(it)
            }
    }

    override fun getTopRate(page: Int) {
        dataSource.getTopRates(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mContract.onTopRateFetched(it.results,it.currentPage,it.totalPage)
            },{
                mContract.onTopRateError(it)
            }).also {
                disposable.add(it)
            }
    }

    override fun setContract(contract: MovieDashboardContract.Contract) {
        mContract = contract
    }

    override fun onStop() {
        disposable.clear()
    }
}