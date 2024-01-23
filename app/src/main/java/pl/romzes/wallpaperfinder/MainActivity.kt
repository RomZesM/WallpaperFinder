package pl.romzes.wallpaperfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import pl.romzes.wallpapers.utils.Connector

class MainActivity : AppCompatActivity(), Connector {

    val TAG = "rmz"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun sendResult(number: Int, text: String) {
        Log.d(TAG, number.toString() + ": " + text.toString())
    }

}