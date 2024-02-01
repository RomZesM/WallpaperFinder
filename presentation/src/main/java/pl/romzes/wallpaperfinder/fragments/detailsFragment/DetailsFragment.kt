package pl.romzes.wallpaperfinder.fragments.detailsFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpapers.utils.Connector

class DetailsFragment : Fragment(), Connector {

    private val TAG = "rmz"
   // private lateinit var imagePreview: ImagePreview
    private  var imageDescription: String? = null
    private  var  imageUrl: String? = null

    companion object {
        fun newInstance(imageUrl: String, imageDescription: String) = DetailsFragment().apply {
            arguments = bundleOf("imgUrl" to imageUrl, "imgDescription" to imageDescription)
        }
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageUrl = arguments?.getString("imgUrl")
        imageDescription = arguments?.getString("imgDescription")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView - detailACt: " + imageDescription + " - " + imageUrl)

        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

    }



}