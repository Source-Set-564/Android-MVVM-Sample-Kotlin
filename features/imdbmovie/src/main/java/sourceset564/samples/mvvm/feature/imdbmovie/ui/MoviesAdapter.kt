package id.nz.app.mvppattern.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.nz.app.mvppattern.data.model.Movie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sourceset564.samples.mvvm.feature.imdbmovie.R
import sourceset564.samples.mvvm.feature.imdbmovie.databinding.ItemListMovieBinding

/**
 * Created by Anwar on 9/25/2019.
 */
class MoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list : MutableList<Movie?> = mutableListOf()
    private var isNextPageShowing = false

    private var disposable : Disposable? = null

    private val TYPE_ITEM = 1
    private val TYPE_NEXT = 2

    private var lastPositionAnimated = -1

    fun updateAdapter(list: List<Movie>){
        disposable?.dispose()
        disposable = Single.create<DiffUtil.DiffResult> {
            val callback = DiffMovieCallback(this.list,list)
            val result = DiffUtil.calculateDiff(callback,true)
            it.onSuccess(result)
        }.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isNextPageShowing = false
                this.list.clear()
                this.list.addAll(list)
                it.dispatchUpdatesTo(this)
            },{
                Log.e("ADR","Computation error")
            })
    }

    fun showNextPageIndicator(show : Boolean){
        if(show && !isNextPageShowing){
            isNextPageShowing = true
            list.add(null)
            notifyItemInserted(list.size)
        }else if(!show && isNextPageShowing){
            val index = list.size - 1
            list.removeAt(index)
            isNextPageShowing = false
            notifyItemRemoved(index)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        disposable?.dispose()
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_ITEM) {
            DataBindingUtil.inflate<ItemListMovieBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_list_movie,
                parent,
                false
            ).apply {
                return ItemViewHolder(this)
            }
        }else{
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_movie_next_page, parent, false).apply {
                    return NetxPageViewHolder(this)
                }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemViewHolder -> {
                if(lastPositionAnimated < position) {
                    lastPositionAnimated = position

                    val animation = AnimationUtils.loadAnimation(
                        holder.itemView.context,
                        R.anim.fall_left)
                    holder.itemView.animation = animation
                }
                list[position]?.also { movie ->
                    holder.binding.movie = movie
                    holder.itemView.setOnClickListener {
                        Toast.makeText(
                            it.context,
                            "${movie.title} has clicked",
                            Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(list[position] == null)
            return TYPE_NEXT
        else{
            return TYPE_ITEM
        }
    }

    inner class DiffMovieCallback(
        val oldList : List<Movie?>,
        val newList : List<Movie?>) : DiffUtil.Callback(){

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val oldItem = oldList[oldPosition]
            val newItem = newList[newPosition]
            if(oldItem == null && newItem != null){
                return false
            }else if (oldItem != null && newItem == null){
                return false
            }else{
                return oldItem!!.id == newItem!!.id
            }
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val oldItem = oldList[oldPosition]
            val newItem = newList[newPosition]
            if(oldItem == null && newItem != null){
                return false
            }else if (oldItem != null && newItem == null){
                return false
            }else{
                return oldItem!!.equals(newItem!!)
            }
        }
    }

    inner class ItemViewHolder(
        val binding : ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root)

    inner class NetxPageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}