package sourceset564.samples.mvvm.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sourceset564.samples.mvvm.feature.imdbmovie.ui.MovieDashboardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment =
            MovieDashboardFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame,fragment)
            .commit()
    }
}
