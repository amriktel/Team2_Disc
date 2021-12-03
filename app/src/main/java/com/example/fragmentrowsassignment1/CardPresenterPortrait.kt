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
//import com.example.fragmentrowsassignment1.MainFragment.ItemViewClickedListener
//import com.example.fragmentrowsassignment1.MainFragment.ItemViewSelectedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
//import com.example.fragmentrowsassignment1.MainFragment.UpdateBackgroundTask
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
//import com.example.fragmentrowsassignment1.CardPresenterSquare.SquareCardViewHolder
import androidx.cardview.widget.CardView
import com.example.fragmentrowsassignment1.BigCardPresenterLand.BigCardItemViewHolder
import com.example.fragmentrowsassignment1.CardPresenterPortrait.PortraitCardViewHolder
//import com.example.fragmentrowsassignment1.CardPresenterLandscape.LandscapeCardViewHolder
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait.BigCardItemViewHolderPort

/*
 * A CardPresenter is used to generate Views and bind Objects to them on demand.
 * It contains an Image CardView
 */
class CardPresenterPortrait : Presenter() {
    private val mDefaultCardImage: Drawable? = null
    private var mContext: Context? = null

    /* private static void updateCardBackgroundColor(ImageCardView view, boolean selected) {
        int color = selected ? sSelectedBackgroundColor : sDefaultBackgroundColor;
        // Both background colors should be set because the view"s background is temporarily visible
        // during animations.
        view.setBackgroundColor(color);
        view.findViewById(R.id.info_field).setBackgroundColor(color);
    }*/
    override fun onCreateViewHolder(parent: ViewGroup): PortraitCardViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.portrait_layout, parent, false)
        mContext = parent.context
        return PortraitCardViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val movie = item as Movie
        val viewHolder1 = viewHolder as PortraitCardViewHolder
        if (movie.title != null) {
            viewHolder1.title.text = movie.title
        }
        viewHolder.mainImage.setBackgroundResource(R.drawable.avatar)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        Log.d(TAG, "onUnbindViewHolder")
    }

    inner class PortraitCardViewHolder(view: View) : ViewHolder(view) {
        var mainImage: ImageView
        var title: TextView
        var cardView: CardView

        init {
            cardView = view.findViewById(R.id.cardview)
            mainImage = view.findViewById(R.id.main_image)
            title = view.findViewById(R.id.title)
        }
    }

    companion object {
        private const val TAG = "CardPresenter"
        private const val CARD_WIDTH = 313
        private const val CARD_HEIGHT = 176
        private const val sSelectedBackgroundColor = 0
        private const val sDefaultBackgroundColor = 0
    }
}