package com.example.reproductorayudantia

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.reproductorayudantia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivityMainBinding
    private var isPlaying : Boolean = false
    private var position : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Log.i("MainActivityReproductor", "onCreate")
        Toast.makeText(this, "Estoy en el onCreate", Toast.LENGTH_SHORT).show()

        binding.playPauseButton.setOnClickListener {

          playOrNotPlayMusic()


           // Toast.makeText("pulse boton", Toast.LENGTH_SHORT).show()
        }

        savedInstanceState?.let {
            position = it.getInt("secondPosition")
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivityReproductor", "onStart")
        mediaPlayer = MediaPlayer.create(this,R.raw.pp_remix)
        //mediaPlayer?.start()    // si lo activo se pone en reproduccion de forma automatica en el onStart
        //isPlaying = true
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivityReproductor", "onResume")
        // recuperar estado cuando la app entre en este ciclo
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
        isPlaying = true



    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivityReproductor", "onPause")
        // mediaPlayer?.stop()
        //mediaPlayer?.release()
        //mediaPlayer = null

        if (mediaPlayer != null)
            position = mediaPlayer!!.currentPosition


    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivityReproductor", "onStop")
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivityReproductor", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivityReproductor", "onDestroy")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("secondPosition", position)
    }

    private fun playOrNotPlayMusic() {
        if(isPlaying){
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            mediaPlayer?.start()
            isPlaying = true
        }

        updateViewMediaPlayer()
    }


    private fun updateViewMediaPlayer(){
        if (isPlaying){

            binding.playPauseButton.text = "Pausa"
            binding.titleTextView.text = "Pretty Play Music - Dua Lippa (texto generado por mi )"

        } else{
            binding.playPauseButton.text = "Play"
            binding.titleTextView.text = "Play Song"

        }


    }

}