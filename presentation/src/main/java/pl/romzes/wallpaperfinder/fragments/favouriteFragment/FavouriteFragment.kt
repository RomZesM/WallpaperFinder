package pl.romzes.wallpaperfinder.fragments.favouriteFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter

class FavouriteFragment : Fragment() {



    companion object {
        fun newInstance() = FavouriteFragment()
    }

    val TAG = "rmz"//todo !del
    private lateinit var viewModel: FavouriteViewModel
    private val rvAdapter = ImagePreviewRVAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}