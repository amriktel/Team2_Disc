package com.example.fragmentrowsassignment1

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
import androidx.cardview.widget.CardView
import com.example.fragmentrowsassignment1.BigCardPresenterLand.BigCardItemViewHolder
import com.example.fragmentrowsassignment1.CardPresenterPortrait.PortraitCardViewHolder
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait.BigCardItemViewHolderPort

/*
 * Main Activity class that loads {@link MainFragment}.
 */
class MainActivity : FragmentActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load fragment
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.rows_frame, MainFragment())
        transaction.commit()
    }
}