package sourceset564.samples.mvvm.feature.imdbmovie.ui

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import id.nz.app.mvppattern.home.MoviesAdapter
import id.nz.template.mvvm.core.BaseFragment
import id.nz.template.mvvm.core.extension.observe
import id.nz.template.mvvm.core.extension.toast
import sourceset564.samples.mvvm.feature.imdbmovie.R
import sourceset564.samples.mvvm.feature.imdbmovie.databinding.FragmentMoviesBinding
import sourceset564.samples.mvvm.feature.imdbmovie.di.DaggerMovieComponent
import sourceset564.samples.mvvm.feature.imdbmovie.di.MovieModule
import javax.inject.Inject

/**
 * Created by Anwar on 10/7/2019.
 */
class MovieDashboardFragment : BaseFragment<FragmentMoviesBinding>(){

    override fun contentView(): Int = R.layout.fragment_movies

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : MovieDashboardViewModel
    private var mSkeletonPopular : SkeletonScreen? = null
    private var mSkeletonTopRate : SkeletonScreen? = null

    private val mPopularAdapter : MoviesAdapter by lazy {
        MoviesAdapter()
    }

    private val mTopRateAdapter : MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun doInjection() {
        DaggerMovieComponent.builder()
            .movieModule(MovieModule(context!!))
            .build()
            .inject(this)
    }

    override fun onViewCreated() {
        viewModel =  ViewModelProvider(this, viewModelFactory)
            .get(MovieDashboardViewModel::class.java)

        LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL,false).also {
            binding.recyclerViewRecomended.layoutManager = it
        }

        val startEndMargin = resources.getDimensionPixelSize(R.dimen.item_movie_margin_start)
        val defaultMargin = resources.getDimensionPixelSize(R.dimen.item_movie_margin)
        val itemDecorator = MovieHorizMarginDecorator(startEndMargin,defaultMargin)

        binding.recyclerViewRecomended.adapter = mPopularAdapter
        binding.recyclerViewRecomended.addItemDecoration(itemDecorator)

        LinearLayoutManager(context!!,LinearLayoutManager.HORIZONTAL,false).also {
            binding.recyclerViewTopRated.layoutManager = it
        }

        binding.recyclerViewTopRated.adapter = mTopRateAdapter
        binding.recyclerViewTopRated.addItemDecoration(itemDecorator)

        binding.swipeRefresh.run {
            setColorSchemeResources(R.color.colorWindowBackground)
            setProgressBackgroundColorSchemeResource(R.color.colorDraggedIndicator)
            setOnRefreshListener {
                viewModel.refresh()
            }
        }

        mSkeletonPopular = Skeleton.bind(binding.recyclerViewRecomended)
            .adapter(mPopularAdapter)
            .shimmer(true)
            .color(R.color.color_skeleton_loading)
            .angle(12)
            .duration(1300)
            .count(6)
            .frozen(true)
            .load(R.layout.item_list_movie_skeleton)
            .show()

        mSkeletonTopRate = Skeleton.bind(binding.recyclerViewTopRated)
            .adapter(mTopRateAdapter)
            .shimmer(true)
            .color(R.color.color_skeleton_loading)
            .angle(12)
            .duration(1300)
            .count(6)
            .frozen(true)
            .load(R.layout.item_list_movie_skeleton)
            .show()

        doObservers()
    }

    private fun doObservers(){
        //Popular Event
        observe(viewModel.loadPopular){
            if(mPopularAdapter.itemCount > 0){
                mPopularAdapter::showNextPageIndicator
            }
        }
        observe(viewModel.listPopular,mPopularAdapter::updateAdapter)
        observe(viewModel.popularErrorMsg) {
            it?.also { toast(it) }
        }

        //Top Rated Event
        observe(viewModel.loadTopRated){
            if(mTopRateAdapter.itemCount > 0) {
                mTopRateAdapter::showNextPageIndicator
            }
        }
        observe(viewModel.listTopRated,mTopRateAdapter::updateAdapter)
        observe(viewModel.topRatedErrorMsg) {
            it?.also { toast(it) }
        }

        //Refresh event
        observe(viewModel.isRefresh){
            binding.swipeRefresh.setRefreshing(it)
            if(!it){
                mSkeletonPopular?.run {
                    hide()
                    mSkeletonPopular = null
                }

                mSkeletonTopRate?.run {
                    hide()
                    mSkeletonTopRate = null
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.stop()
    }
}