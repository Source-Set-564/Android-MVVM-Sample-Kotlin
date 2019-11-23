package sourceset564.samples.mvvm.feature.imdbmovie.di

import dagger.Component
import sourceset564.samples.mvvm.feature.imdbmovie.ui.MovieDashboardFragment

/**
 * Created by Anwar on 11/22/2019.
 */

@MovieScope
@Component(modules = [
    MovieModule::class,
    ViewModelModule::class
])
interface MovieComponent {
    fun inject(fragment : MovieDashboardFragment)
}