package id.nz.app.mvppattern.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Anwar on 11/07/2019.
 */
data class Movie(

    @SerializedName("id")
    var id : Int,

    @SerializedName("title")
    var title : String) : Serializable

{

    @SerializedName("poster_path")
    var image : String? = null

    @SerializedName("vote_average")
    var voteAvg : String? = null

    @SerializedName("overview")
    var overview : String? = null

    @SerializedName("release_date")
    var releaseDate : String? = null

    fun getRating() : Float {
        return voteAvg?.toFloat()?.div(2f) ?: 0f
    }
}
