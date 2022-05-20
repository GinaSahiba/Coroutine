package com.example.coroutinedrummachine

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var anji: MediaPlayer
    private lateinit var prilly: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anji = MediaPlayer.create(this, R.raw.anji)
        prilly = MediaPlayer.create(this, R.raw.prilly)

        btnStart.setOnClickListener {
            runBlocking {
                launch { playBeats("x-x-x-x-x-x-x-x-x-x-x-x-", R.raw.prilly) }
                playBeats("x-----x-----x-----x-----", R.raw.anji)
            }
        }
    }
    suspend fun playBeats(beats: String, fileId: Int){
        val parts = beats.split("x")
        var count = 0
        for(part in parts){
            count += part.length + 1
            if(part == ""){
                if(fileId == R.raw.anji)
                    anji.start()
                else
                    prilly.start()
            }else{
                delay(1000 * (part.length + 1L))
                if(count < beats.length){
                    if(fileId == R.raw.anji)
                        anji.start()
                    else
                        prilly.start()
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        anji.stop()
        prilly.stop()
    }
}