package com.example.fragmentrowsassignment1

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_CONTROL_TYPE_KEY
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_PIP
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_TYPE_PAUSE
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_TYPE_PLAY
import com.example.fragmentrowsassignment1.DemoConstant.PAUSE
import com.example.fragmentrowsassignment1.DemoConstant.PLAY

class PlayerFragment : VideoSupportFragment() {
    private val TAG = PlayerFragment::class.simpleName
    var movieItem : Movie? = null
    var playbackTransportControlGlue : PlaybackTransportControlGlue<MediaPlayerAdapter>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieItem = requireActivity().intent.getSerializableExtra("movie") as Movie
        var glueHost = VideoSupportFragmentGlueHost(this)
        var mediaAdapter = MediaPlayerAdapter(activity)
        mediaAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)
        playbackTransportControlGlue = PlaybackTransportControlGlue(activity, mediaAdapter)

        playbackTransportControlGlue?.host = glueHost
        playbackTransportControlGlue?.title = movieItem?.title
        playbackTransportControlGlue?.subtitle = movieItem?.description
        playbackTransportControlGlue!!.playWhenPrepared()

        mediaAdapter.setDataSource(Uri.parse(movieItem?.videoUrl))
        var pipAction = PlaybackControlsRow.PictureInPictureAction(activity)

    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(broadcastReceiver)
    }

    override fun onStart() {
        super.onStart()
        if (playbackTransportControlGlue != null)
            playbackTransportControlGlue!!.play()
        activity?.registerReceiver(broadcastReceiver, IntentFilter(ACTION_PIP))
    }

    override fun onStop() {
        super.onStop()
        if (playbackTransportControlGlue != null)
            playbackTransportControlGlue!!.pause()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
        if (isInPictureInPictureMode){
            hideControlsOverlay(true)
        }else{
            showControlsOverlay(true)
        }
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            if (intent != null && ACTION_PIP.equals(intent.action,true)){
                var type = intent.getIntExtra(ACTION_CONTROL_TYPE_KEY, 0)
                when(type){
                    ACTION_TYPE_PAUSE -> {
                        playbackTransportControlGlue!!.pause()
                        updatePipAction(PLAY, ACTION_TYPE_PLAY, R.drawable.ic_play_arrow_24dp)
                    }
                    ACTION_TYPE_PLAY -> {
                        playbackTransportControlGlue!!.play()
                        updatePipAction(PAUSE, ACTION_TYPE_PAUSE, R.drawable.ic_pause_24dp)
                    }

                }
            }
        }

    }

    @SuppressLint("NewApi")
    private fun updatePipAction(actionTypeToShow : String, actionType: Int, resId : Int){

        val intent : PendingIntent = PendingIntent.getBroadcast(
            activity,
            actionType,
            Intent(ACTION_PIP).putExtra(ACTION_CONTROL_TYPE_KEY, actionType),
            PendingIntent.FLAG_IMMUTABLE)
        val icon : Icon = Icon.createWithResource(activity, resId)
        val remoteAction = RemoteAction(icon, actionTypeToShow, actionTypeToShow, intent)
        activity?.setPictureInPictureParams(PictureInPictureParams.Builder()
            .setActions(listOf(remoteAction)).build())

    }

}