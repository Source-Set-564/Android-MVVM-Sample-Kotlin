package sourceset564.samples.mvvm.feature.imdbmovie.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Anwar on 11/24/2019.
 */
class MovieHorizMarginDecorator(val startEnd : Int, val margin : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if(parent.getChildAdapterPosition(view) == 0){
            outRect.left = startEnd
        }else{
            outRect.left = margin / 2
        }
        val isEnd = parent.getChildAdapterPosition(view) == parent.adapter?.let {
            it.itemCount - 1
        } ?: false

        if(isEnd){
            outRect.right = startEnd
        }else{
            outRect.right = margin / 2
        }
    }
}