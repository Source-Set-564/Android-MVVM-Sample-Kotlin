package sourceset564.samples.mvvm.feature.imdbmovie.domain

import id.nz.app.mvppattern.data.model.Movie
import id.nz.template.mvvm.core.BaseUseCase

/**
 * Created by Anwar on 11/22/2019.
 */

interface MovieDashboardContract{

    interface UseCase : BaseUseCase<Contract>{
        fun getPopular(page : Int)

        fun getTopRate(page: Int)
    }

    interface Contract{
        fun onPopularFetched(list: List<Movie>, currentPage : Int, countPage : Int)

        fun onPopularError(throwable: Throwable)

        fun onTopRateFetched(list: List<Movie>, currentPage : Int, countPage: Int)

        fun onTopRateError(throwable: Throwable)
    }
}