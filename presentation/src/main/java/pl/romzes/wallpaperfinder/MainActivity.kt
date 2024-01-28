package pl.romzes.wallpaperfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragment


import pl.romzes.wallpapers.utils.Connector

class MainActivity : AppCompatActivity(), Connector {

    val TAG = "rmz"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    //todo -test fun, remove later
    override fun sendResult(number: Int, text: String) {
        val resultFragment : ResultFragment = ResultFragment()
        resultFragment.someResultData = text
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_id, resultFragment)
            commit()
        }

        Log.d(TAG, number.toString() + ": " + text.toString())
    }

}