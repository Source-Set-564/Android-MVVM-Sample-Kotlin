/**
 * Created by Anwar on 10/7/2019.
 */
object Modules{

    val app = ":app"

    //features
    val movie = ":imdbmovie"

    //libs or some base or utils modules
    val coreUtils = ":core-utils"
}

object ModuleFilesLocation{
    //feature
    private val features = "features"
    private val libraries = "libraries"

    val movies = features + "/" + Modules.movie.let {
        it.substring(1,it.length)
    }

    val coreUtils = libraries + "/" +  Modules.coreUtils.let {
        it.substring(1,it.length)
    }
}