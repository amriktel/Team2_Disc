package com.example.fragmentrowsassignment1

import android.content.Context
import com.example.fragmentrowsassignment1.MovieList
import androidx.fragment.app.FragmentActivity
import android.os.Bundle
import com.example.fragmentrowsassignment1.R
import com.example.fragmentrowsassignment1.MainFragment
import androidx.leanback.app.RowsSupportFragment
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import androidx.leanback.app.BackgroundManager
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRowPresenter
import com.example.fragmentrowsassignment1.CardPresenterPortrait
import com.example.fragmentrowsassignment1.CardPresenterLandscape
import com.example.fragmentrowsassignment1.CardPresenterSquare
import com.example.fragmentrowsassignment1.BigCardPresenterLand
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowPresenter
import androidx.leanback.widget.OnItemViewSelectedListener
import android.view.ViewGroup
import android.widget.TextView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.fragmentrowsassignment1.BigCardPresenterLand.BigCardItemViewHolder
import com.example.fragmentrowsassignment1.CardPresenterPortrait.PortraitCardViewHolder
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait.BigCardItemViewHolderPort

class BigCardPresenterPortrait(private val mContext: Context?) : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.big_portrait_item, parent, false)
        return BigCardItemViewHolderPort(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val movieData = item as Movie
        val holder = viewHolder as BigCardItemViewHolderPort
        if (movieData == null) return
        if (holder.textView != null) holder.textView!!.setText(movieData.title)
        if (holder.imageView != null) Glide.with(mContext)
            .load(movieData.cardImageUrl)
            .centerCrop()
            .error(R.drawable.movie)
            .into(holder.imageView)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        Log.d(TAG, "onUnbindViewHolder")
    }

    internal inner class BigCardItemViewHolderPort(view: View) : ViewHolder(view) {
        var cardView: CardView
        var imageView: ImageView?
        var textView: TextView?

        init {
            cardView = view.findViewById(R.id.card_view_container)
            imageView = view.findViewById(R.id.movie_image)
            textView = view.findViewById(R.id.movie_title)
        }
    }

    companion object {
        private val TAG = BigCardPresenterPortrait::class.java.simpleName
    }
}