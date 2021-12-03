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
import com.example.fragmentrowsassignment1.CardPresenterLandscape.LandscapeCardViewHolder
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait.BigCardItemViewHolderPort

class CardPresenterLandscape : Presenter() {
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.landscape_layout, parent, false)
        mContext = parent.context
        return LandscapeCardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val movie = item as Movie
        val viewHolder1 = viewHolder as LandscapeCardViewHolder
        if (movie.title != null) {
            viewHolder1.title.text = movie.title
        }
        viewHolder.mainImage.setBackgroundResource(R.drawable.frozen)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
    private inner class LandscapeCardViewHolder(view: View) : ViewHolder(view) {
        var mainImage: ImageView
        var title: TextView
        var cardView: CardView

        init {
            cardView = view.findViewById(R.id.cardview)
            mainImage = view.findViewById(R.id.main_image)
            title = view.findViewById(R.id.title)
        }
    }
}