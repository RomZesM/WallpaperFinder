package pl.romzes.wallpaperfinder.fragments.detailsFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.utils.getImageFromURL
import pl.romzes.wallpapers.utils.Connector

class DetailsFragment : Fragment(), Connector {

    private val TAG = "rmz"
   // private lateinit var imagePreview: ImagePreview
    private  var imageDescription: String? = null
    private  var imageUrl: String? = null
    private  var width: String? = null
    private  var height: String? = null

    companion object {
        fun newInstance(imageUrl: String, imageDescription: String, width : String, height : String) = DetailsFragment().apply {
            arguments = bundleOf("imgUrl" to imageUrl,
                                "imgDescription" to imageDescription,
                                "width" to width,
                                "height" to height)
        }
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageUrl = arguments?.getString("imgUrl")
        imageDescription = arguments?.getString("imgDescription")
        width = arguments?.getString("width")
        height = arguments?.getString("height")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity : MainActivity = (requireActivity() as MainActivity)
        //Change text in toolbox
        activity.findViewById<Toolbar>(R.id.toolbar_id)?.title = getString(R.string.detail_fragment_title)
        //show back button
        activity.showUpButton(true)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val rootView = inflater.inflate(R.layout.fragment_details, container, false)

        //set text and image into fragment
        val imageView = rootView.findViewById<ImageView>(R.id.detail_image_id)
        val imageDescField  = rootView.findViewById<TextView>(R.id.detail_description_id)
        val widthField = rootView.findViewById<TextView>(R.id.width_id)
        val heigthGield = rootView.findViewById<TextView>(R.id.height_id)
        //todo make it with view model
        imageUrl?.let { imageView.getImageFromURL(requireActivity() as MainActivity, it) }
        imageDescField.text = imageDescription
        widthField.text = width
        heigthGield.text = height

        return rootView
    }

}