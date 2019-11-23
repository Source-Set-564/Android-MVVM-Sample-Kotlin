package id.nz.app.mvppattern.data

import id.nz.app.mvppattern.data.model.ListModel
import id.nz.app.mvppattern.data.model.Movie
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anwar on 9/16/2019.
 */
interface MovieEndpoint {

    @GET("movie/top_rated")
    fun topRate(@Query("page") page : Int) : Observable<ListModel<Movie>>

    @GET("movie/popular")
    fun popular(@Query("page") page : Int) : Observable<ListModel<Movie>>
}