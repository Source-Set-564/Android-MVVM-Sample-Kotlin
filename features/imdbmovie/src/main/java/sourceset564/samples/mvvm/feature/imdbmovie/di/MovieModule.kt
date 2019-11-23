package sourceset564.samples.mvvm.feature.imdbmovie.di

import android.content.Context
import dagger.Module
import dagger.Provides
import sourceset564.samples.mvvm.feature.imdbmovie.data.source.MovieDataSource
import sourceset564.samples.mvvm.feature.imdbmovie.data.source.MovieRepository
import sourceset564.samples.mvvm.feature.imdbmovie.domain.MovieDashboardContract
import sourceset564.samples.mvvm.feature.imdbmovie.domain.MovieDashboardUseCase

/**
 * Created by Anwar on 11/22/2019.
 */

@Module
class MovieModule(val context: Context) {

    @MovieScope
    @Provides
    fun provideDataSource() : MovieDataSource{
        return MovieRepository(context)
    }

    @MovieScope
    @Provides
    fun provideMovieDashboardUseCase(dataSource: MovieDataSource) : MovieDashboardContract.UseCase{
        return MovieDashboardUseCase(dataSource)
    }
}