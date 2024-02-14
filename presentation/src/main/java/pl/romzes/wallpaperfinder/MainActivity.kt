package pl.romzes.wallpaperfinder


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import pl.romzes.wallpaperfinder.fragments.favouriteFragment.FavouriteFragment
import pl.romzes.wallpaperfinder.fragments.searchFragment.SearchFieldFragment
import pl.romzes.wallpapers.utils.Connector

class MainActivity : AppCompatActivity(), Connector {

    val TAG = "rmz"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContentView(R.layout.activity_main)
        //init toolbar
        val toolbar : Toolbar = findViewById(R.id.toolbar_id)
        setSupportActionBar(toolbar)

        displayFragment(SearchFieldFragment())
    }

    //turnOn/Off the Up Arrow
    fun showUpButton(state : Boolean){
       supportActionBar?.setDisplayHomeAsUpEnabled(state)
    }
    override fun onSupportNavigateUp(): Boolean {
       // onBackPressed()
       onBackPressedDispatcher.onBackPressed()
        return true
    }

    //add menu to ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //add listener for menu in action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_open_fav_fragment_id){
           displayFragment(FavouriteFragment())
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_id, fragment)
            addToBackStack(fragment.tag)
            commit()
        }
    }

}