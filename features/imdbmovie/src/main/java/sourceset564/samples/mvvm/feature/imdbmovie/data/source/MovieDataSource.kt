package sourceset564.samples.mvvm.feature.imdbmovie.data.source

import id.nz.app.mvppattern.data.model.ListModel
import id.nz.app.mvppattern.data.model.Movie
import io.reactivex.Observable

/**
 * Created by Anwar on 11/22/2019.
 */
interface MovieDataSource {

    fun getPopulars(page : Int) : Observable<ListModel<Movie>>

    fun getTopRates(page : Int) : Observable<ListModel<Movie>>
}