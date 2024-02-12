package pl.romzes.wallpaperfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragment
import pl.romzes.wallpaperfinder.fragments.searchFragment.SearchFieldFragment


import pl.romzes.wallpapers.utils.Connector

class MainActivity : AppCompatActivity(), Connector {

    val TAG = "rmz"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //init toolbar
        val toolbar : Toolbar = findViewById(R.id.toolbar_id)
        setSupportActionBar(toolbar)

        displayFragment(SearchFieldFragment())
    }


    //todo -test fun, remove later
    fun displayFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_id, fragment)
            addToBackStack(fragment.tag)
            commit()
        }

    }

}