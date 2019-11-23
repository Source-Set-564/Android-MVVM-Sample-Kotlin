package sourceset564.samples.mvvm.feature.imdbmovie.data.source

import android.content.Context
import id.nz.app.mvppattern.data.MovieEndpoint
import id.nz.app.mvppattern.data.model.ListModel
import id.nz.app.mvppattern.data.model.Movie
import id.nz.template.mvvm.core.retrofit.RetrofitUtils
import io.reactivex.Observable
import okhttp3.logging.HttpLoggingInterceptor
import sourceset564.samples.mvvm.feature.imdbmovie.BuildConfig
import sourceset564.samples.mvvm.feature.imdbmovie.R
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Anwar on 11/22/2019.
 */
@Singleton
class MovieRepository @Inject constructor(context: Context) : MovieDataSource{

    private val services : MovieEndpoint

    init {
        val baseUrl = context.resources.getString(R.string.movie_base_url)
        val key = context.resources.getString(R.string.movie_api_key)

        val logLevel =
            if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else null

        val client = RetrofitUtils.okHttpClientWithKey(
            "api_key",
            key,
            RetrofitUtils.KeyParameter.QUERY,
            logLevel)

        services = RetrofitUtils.createServiceWithClient(baseUrl,MovieEndpoint::class.java,client)
    }

    override fun getPopulars(page: Int): Observable<ListModel<Movie>> {
        return services.popular(page)
    }

    override fun getTopRates(page: Int): Observable<ListModel<Movie>> {
        return services.topRate(page)
    }
}