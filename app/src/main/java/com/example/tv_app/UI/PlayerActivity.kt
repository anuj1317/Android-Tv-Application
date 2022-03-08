package com.example.tv_app.UI

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.FragmentActivity
import com.example.tv_app.R
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util


class PlayerActivity : FragmentActivity() {

    var player: ExoPlayer? = null
    lateinit var playerView: PlayerView
    var playWhenReady = true
    var currentWindow = 0
    var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        playerView = findViewById(R.id.player_view)

    }

    private fun initialzePlayer() {
        val trackSelector = DefaultTrackSelector(this)
        trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd().build())

        player = SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build()


        playerView.player = player
        player?.playWhenReady = playWhenReady

/*
        val mediaItem =
            MediaItem.fromUri("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8")
*/

        val mediaItem = MediaItem.Builder().setUri("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8")
            .setMimeType(MimeTypes.APPLICATION_M3U8).build()

        val mediaItem1 = MediaItem.Builder().setUri("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8")
            .setMimeType(MimeTypes.APPLICATION_M3U8).build()

        player?.addMediaItem(mediaItem)
        player?.addMediaItem(mediaItem1)

        player?.seekTo(currentWindow, playbackPosition)
//        player?.addListener(playerEvent() as Player.Listener)
//        (player as SimpleExoPlayer)?.addListener(playerEvent() as Player.Listener)
        player?.prepare()
    }

    private fun releasePlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
        }
        player = null
    }

//    fun playerEvent() = object: Player.EventListener{
//
//        override fun onPlaybackStateChanged(state: Int) {
//            Log.d("tag","Player  onPlaybackStateChanged ${state}")
//
//            when(state){
//                ExoPlayer.STATE_BUFFERING ->{
//                    Log.d("tag","Player  bufffereing ${state}")
//                }
//                ExoPlayer.STATE_IDLE ->{
//                    Log.d("tag","Player  idle ${state}")
//                }
//
//                ExoPlayer.STATE_READY ->{
//                    Log.d("tag","Player  ready ${state}")
//                }
//                ExoPlayer.STATE_ENDED ->{
//                    Log.d("tag","Player  end ${state}")
//                }
//            }
//        }
//
//        override fun onIsPlayingChanged(isPlaying: Boolean) {
//            Log.d("tag","Player  onIsPlayingChanged ${isPlaying}")
//        }
//
//        override fun onPlayerError(error: ExoPlaybackException) {
//            Log.d("tag","Player  onIsPlayingChanged ${error.message}")
//        }
//
//    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initialzePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || player == null) {
            initialzePlayer()
        }
    }


    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }

    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("tag","Player  onkeyDown ${keyCode}")
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (player?.isPlaying == true) {
                player?.pause()
            } else {
                player?.play()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}