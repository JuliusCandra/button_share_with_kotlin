package com.dev_candra.button_share

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Aksi()
    }

    private fun Aksi(){
        shareText()
        shareImage()
    }

    // mengirim text
    private fun shareText(){
        button1.apply {
            setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                val text = teks1.text.toString()
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT,"Judul")
                intent.putExtra(Intent.EXTRA_TEXT,text)
                startActivity(Intent.createChooser(intent,"Share via"))
            }
        }
    }

    // mengirim gambar
    @SuppressLint("SetWorldReadable")
    private fun shareImage(){
        button2.apply {
            setOnClickListener {
                val gambar = image1.drawable
                val bitmap = (gambar as BitmapDrawable).bitmap
                val file = File(externalCacheDir,"candra.png")
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG,90,out)
                file.setReadable(true,false)
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/png"
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(Intent.EXTRA_SUBJECT,"judul")
                intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file))
                startActivity(Intent.createChooser(intent,"share via"))
            }
        }

    }
    private fun Aksi1(){
        // percobaan pertama
        // get image as bitmap form imageview
        val myDrawable = image1.drawable
        val bitmap = (myDrawable as BitmapDrawable).bitmap
        // image share
        val file = File(externalCacheDir,"candra.png")
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG,90,out)
        out.flush()
        out.close()
        file.setReadable(true,false)
        val intent = Intent(Intent.ACTION_SEND)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
        intent.type = "image/png"
        intent.putExtra(Intent.EXTRA_SUBJECT,"Judul")
        startActivity(Intent.createChooser(intent,"Share Via"))

        // percobaan kedua
        // get text from textview and store in variable
        val text1 = teks1.text.toString()
        // Intent to share the text
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT,text1)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"subject here")
        startActivity(Intent.createChooser(shareIntent,"Share text in via"))
    }
}
