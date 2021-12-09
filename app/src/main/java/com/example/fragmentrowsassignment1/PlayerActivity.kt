package com.example.fragmentrowsassignment1

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Rational
import androidx.fragment.app.FragmentActivity
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_CONTROL_TYPE_KEY
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_PIP
import com.example.fragmentrowsassignment1.DemoConstant.ACTION_TYPE_PAUSE
import com.example.fragmentrowsassignment1.DemoConstant.PAUSE

class PlayerActivity : FragmentActivity() {

    var mContext : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val playerFragment = PlayerFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.player_activity_container, playerFragment).commit()

        mContext = this
    }

    @SuppressLint("NewApi")
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val param = PictureInPictureParams.Builder()
                    .setActions(getAction())
                    .setAspectRatio(getAspectRatio())
                    .build()
//            setPictureInPictureParams(param)
            enterPictureInPictureMode(param)
        }
    }

    private fun getAspectRatio() : Rational {
        val width :Int = window.decorView.width
        val height :Int = window.decorView.height
        return Rational(width, height)
    }

    @SuppressLint("NewApi")
    private fun getAction() : List<RemoteAction> {

        val intent : PendingIntent = PendingIntent.getBroadcast(
                    this,
                    ACTION_TYPE_PAUSE,
                    Intent(ACTION_PIP).putExtra(ACTION_CONTROL_TYPE_KEY, ACTION_TYPE_PAUSE),
                    PendingIntent.FLAG_IMMUTABLE)
        val icon : Icon = Icon.createWithResource(this, R.drawable.ic_pause_24dp)
        val remoteAction = RemoteAction(icon, PAUSE, PAUSE, intent)
//
//        val intent1 : PendingIntent = PendingIntent.getBroadcast(
//                    this,
//                    2,
//                    Intent("pip_media_control").putExtra("control_type", 2),
//                    PendingIntent.FLAG_IMMUTABLE)
//        val icon1 : Icon = Icon.createWithResource(this, R.drawable.ic_pause_24dp)
//        val remoteAction1 = RemoteAction(icon1, "pause", "pause", intent1)

        return listOf(remoteAction/*, remoteAction1*/ )
    }

    @SuppressLint("NewApi")
    override fun onBackPressed() {
        super.onBackPressed()
        if (mContext != null && mContext is PlayerActivity){
            val intent = Intent(mContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            finish()
            startActivity(intent)
        }
    }
}