package pl.romzes.wallpaperfinder.utils

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

fun ImageView.getImageFromURL(activity : AppCompatActivity, adress : String){
        Glide.with(activity).asDrawable().load(adress).into(this)
    }

    fun ImageView.getImageFromURLOverride(activity : AppCompatActivity, adress : String, width : Int, height : Int){
        Glide.with(activity)
            .load(adress)
            .override(width, height)
            .into(this)
    }
