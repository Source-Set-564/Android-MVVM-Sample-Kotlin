package sourceset564.samples.mvvm.feature.imdbmovie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.nz.template.mvvm.core.ViewModelFactory
import id.nz.template.mvvm.core.ViewModelKey
import sourceset564.samples.mvvm.feature.imdbmovie.ui.MovieDashboardViewModel

/**
 * Created by Anwar on 11/22/2019.
 */

@Module
abstract class ViewModelModule {

    @MovieScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory : ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieDashboardViewModel::class)
    internal abstract fun bindViewModel(viewModel : MovieDashboardViewModel) : ViewModel
}