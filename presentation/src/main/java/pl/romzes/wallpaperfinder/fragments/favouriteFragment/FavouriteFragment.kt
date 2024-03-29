package pl.romzes.wallpaperfinder.fragments.favouriteFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import pl.romzes.wallpaperfinder.app.App
import pl.romzes.wallpaperfinder.fragments.detailsFragment.DetailsFragment
import pl.romzes.wallpaperfinder.utils.MyRecyclerViewOnClickListener
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteFragment()
    }

    val TAG = "rmz"//todo !del

    @Inject
    lateinit var favouriteViewModelFactory : FavouriteViewModelFactory

    private lateinit var viewModel: FavouriteViewModel
    private var spanCountInRV = 2

    private val rvAdapter = ImagePreviewRVAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dagger injection for activity
        (activity?.applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, favouriteViewModelFactory).get(FavouriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Change text in toolbox
        (requireActivity() as MainActivity).findViewById<Toolbar>(R.id.toolbar_id)?.title = getString(R.string.favourite_fragment)
        //show bask button only in fragment
        (requireActivity() as MainActivity).showUpButton(true);

        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onStart() {
        super.onStart()
        spanCountInRV = if(this.resources.configuration.orientation == 1){
            2
        } else
            5

        context?.let { viewModel.getImagesFromDB(it) }

        // TODO: Use the ViewModel
        viewModel.imagelist.observe(viewLifecycleOwner) {
            initRecyclerView()
        }

    }

    //todo make separate function for both fragment as util - refactor
    private fun initRecyclerView() {
        //set myonclickFunction in adapter
        rvAdapter.setMyOnclickListener(object : MyRecyclerViewOnClickListener {
            //on click we open new fragment with parameters of images
            //todo add some more parameters
            override fun onClick(position: Int) {
                val imagePreview = viewModel.imagelist.value?.get(position)

                (requireActivity() as MainActivity).displayFragment(DetailsFragment.newInstance(
                    imageUrl = imagePreview?.imageUrl.toString(),
                    imageDescription = imagePreview?.description.toString(),
                    width =  imagePreview?.width.toString(),
                    height =  imagePreview?.height.toString()
                ))
            }
            override fun favOnClick(image : ImagePreview, position : Int) {
                //save fav image in db //todo can i get context, not context?
                context?.let { viewModel.deleteFavImageFromDb(it, image, position) }
                //refresh recycler view
                viewModel.imagelist.value?.let { rvAdapter.setImagePreviewIntoRecyclerView(it.toList()) }
            }
        })

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_id) //get the recycler view from fragment
        //config recyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, spanCountInRV) //set options for display elements on view
        recyclerView?.adapter = rvAdapter //attach adapter, we can change different adapters for display info in RV
        viewModel.imagelist.value?.let { rvAdapter.setImagePreviewIntoRecyclerView(it)
        }
    }

}