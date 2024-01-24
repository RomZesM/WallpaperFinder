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
        val resultFragment : SearchResultFragment = SearchResultFragment()
        resultFragment.someResultData = text
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_id, resultFragment)
            commit()
        }

        Log.d(TAG, number.toString() + ": " + text.toString())
    }

}