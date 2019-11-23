package id.nz.app.mvppattern.data.model
import com.google.gson.annotations.SerializedName

/**
 * Created by Anwar on 11/07/2019.
 */
data class ListModel<T>(
    @SerializedName("page")
    var currentPage : Int = 1,

    @SerializedName("total_pages")
    var totalPage : Int = 1,

    @SerializedName("total_results")
    var totalResult : Int = 1,

    @SerializedName("results")
    var results : List<T>)