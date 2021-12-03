package com.example.fragmentrowsassignment1

import android.graphics.Color
import com.example.fragmentrowsassignment1.MovieList
import androidx.fragment.app.FragmentActivity
import android.os.Bundle
import com.example.fragmentrowsassignment1.R
import com.example.fragmentrowsassignment1.MainFragment
import androidx.leanback.app.RowsSupportFragment
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import androidx.leanback.app.BackgroundManager
import com.example.fragmentrowsassignment1.CardPresenterPortrait
import com.example.fragmentrowsassignment1.CardPresenterLandscape
import com.example.fragmentrowsassignment1.CardPresenterSquare
import com.example.fragmentrowsassignment1.BigCardPresenterLand
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait
import androidx.core.content.ContextCompat
import com.example.fragmentrowsassignment1.MainFragment.ItemViewClickedListener
import com.example.fragmentrowsassignment1.MainFragment.ItemViewSelectedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.example.fragmentrowsassignment1.MainFragment.UpdateBackgroundTask
import android.view.ViewGroup
import android.widget.TextView
import android.view.Gravity
import android.view.LayoutInflater
//import com.example.fragmentrowsassignment1.CardPresenterSquare.SquareCardViewHolder
import androidx.cardview.widget.CardView
import androidx.leanback.widget.*
import com.example.fragmentrowsassignment1.BigCardPresenterLand.BigCardItemViewHolder
import com.example.fragmentrowsassignment1.CardPresenterPortrait.PortraitCardViewHolder
//import com.example.fragmentrowsassignment1.CardPresenterLandscape.LandscapeCardViewHolder
import com.example.fragmentrowsassignment1.BigCardPresenterPortrait.BigCardItemViewHolderPort
import java.util.*

class MainFragment : RowsSupportFragment() {
    private val mHandler = Handler()
    private var mDefaultBackground: Drawable? = null
    private var mMetrics: DisplayMetrics? = null
    private var mBackgroundTimer: Timer? = null
    private var mBackgroundUri: String? = null
    private var mBackgroundManager: BackgroundManager? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onActivityCreated(savedInstanceState)
        //prepareBackgroundManager()

        // setupUIElements();
        loadRows()
        setupEventListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (null != mBackgroundTimer) {
            Log.d(TAG, "onDestroy: " + mBackgroundTimer.toString())
            mBackgroundTimer!!.cancel()
        }
    }

    private fun loadRows() {
        val list = MovieList.setupMovies()
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenterPortrait = CardPresenterPortrait()
        val cardPresenterLandscape = CardPresenterLandscape()
        val cardPresenterSquare = CardPresenterSquare()
        val bigCardPresenterLand = BigCardPresenterLand(context)
        val bigCardPresenterPortrait = BigCardPresenterPortrait(context)
        var i: Int
        i = 0
        while (i < NUM_ROWS) {
            val header = HeaderItem(i.toLong(), MovieList.MOVIE_CATEGORY[i])
            var listRowAdapter: ArrayObjectAdapter
            listRowAdapter = when (i) {
                0 -> ArrayObjectAdapter(cardPresenterPortrait)
                1 -> ArrayObjectAdapter(cardPresenterLandscape)
                2 -> ArrayObjectAdapter(cardPresenterSquare)
                3 -> ArrayObjectAdapter(bigCardPresenterLand)
                4 -> ArrayObjectAdapter(bigCardPresenterPortrait)
                else -> ArrayObjectAdapter(cardPresenterPortrait)
            }
            for (j in 0 until NUM_COLS) {
                listRowAdapter.add(list!![j % 5])
            }
            rowsAdapter.add(ListRow(header, listRowAdapter))
            i++
        }

        /*HeaderItem gridHeader = new HeaderItem(i, "PREFERENCES");

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add(getResources().getString(R.string.grid_view));
        gridRowAdapter.add(getString(R.string.error_fragment));
        gridRowAdapter.add(getResources().getString(R.string.personal_settings));
        rowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));*/adapter = rowsAdapter
    }

    /*private fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(activity!!.window)
        mDefaultBackground = ContextCompat.getDrawable(activity!!, R.drawable.default_background)
        mMetrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(mMetrics)
    }*/

    private fun setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(
        // R.drawable.videos_by_google_banner));
        //     setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent
        // over title
        //     setHeadersState(HEADERS_ENABLED);
        //    setHeadersTransitionOnBackEnabled(true);

        // set fastLane (or headers) background color
        //    setBrandColor(ContextCompat.getColor(getActivity(), R.color.fastlane_background));
        // set search icon color
        //    setSearchAffordanceColor(ContextCompat.getColor(getActivity(), R.color.search_opaque));
    }

    private fun setupEventListeners() {
        /* setOnSearchClickedListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Implement your own in-app search", Toast.LENGTH_LONG)
                        .show();
            }
        });*/
        onItemViewClickedListener = ItemViewClickedListener()
        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    private fun updateBackground(uri: String?) {
//        val width = mMetrics!!.widthPixels
//        val height = mMetrics!!.heightPixels
        /*Glide.with(activity)
            .load(uri)
            .centerCrop()
            .error(mDefaultBackground)
            .into(object : SimpleTarget<GlideDrawable?>(width, height) {
                override fun onResourceReady(
                    resource: GlideDrawable,
                    glideAnimation: GlideAnimation<in GlideDrawable>
                ) {
                    mBackgroundManager!!.drawable = resource
                }
            })
        mBackgroundTimer!!.cancel()*/
    }

    private fun startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer!!.cancel()
        }
        mBackgroundTimer = Timer()
        mBackgroundTimer!!.schedule(UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY.toLong())
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder ?, item: Any ?,
            rowViewHolder: RowPresenter.ViewHolder ?, row: Row ?
        ) {
            if (item is Movie) {
                val movie = item
                Log.d(TAG, "Item: $item")
                /* Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.MOVIE, movie);

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
                        DetailsActivity.SHARED_ELEMENT_NAME)
                        .toBundle();
                getActivity().startActivity(intent, bundle);*/
            }
        }
    }

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder ?,
            item: Any ?,
            rowViewHolder: RowPresenter.ViewHolder ?,
            row: Row ?
        ) {
            if (item is Movie) {
                mBackgroundUri = item.backgroundImageUrl
                startBackgroundTimer()
            }
        }
    }

    private inner class UpdateBackgroundTask : TimerTask() {
        override fun run() {
            mHandler.post { updateBackground(mBackgroundUri) }
        }
    }

    private inner class GridItemPresenter : Presenter() {
        override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
            val view = TextView(parent.context)
            view.layoutParams = ViewGroup.LayoutParams(
                GRID_ITEM_WIDTH,
                GRID_ITEM_HEIGHT
            )
            view.isFocusable = true
            view.isFocusableInTouchMode = true
            view.setBackgroundColor(
                ContextCompat.getColor(activity!!, R.color.default_background)
            )
            view.setTextColor(Color.WHITE)
            view.gravity = Gravity.CENTER
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
            (viewHolder.view as TextView).text = item as String
        }

        override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
    }

    companion object {
        private const val TAG = "MainFragment"
        private const val BACKGROUND_UPDATE_DELAY = 300
        private const val GRID_ITEM_WIDTH = 200
        private const val GRID_ITEM_HEIGHT = 200
        private const val NUM_ROWS = 6
        private const val NUM_COLS = 10
    }
}