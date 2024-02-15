package pl.romzes.wallpaperfinder.fragments.favouriteFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import pl.romzes.wallpaperfinder.fragments.detailsFragment.DetailsFragment
import pl.romzes.wallpaperfinder.utils.MyRecyclerViewOnClickListener

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
        viewModel.imagelist.observe(viewLifecycleOwner) {
          initRecyclerView()
        }
        //show bask button only in fragment
        (requireActivity() as MainActivity).showUpButton(true);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        context?.let { viewModel.getImagesFromDB(it) }
    }
    //todo make separate function for both fragment as util - refactor
    private fun initRecyclerView() {
        //set myonclickFunction in adapter
        rvAdapter.setMyOnclickListener(object : MyRecyclerViewOnClickListener {
            //on click we open new fragment with parameters of images
            //todo add some more parameters
            override fun onClick(position: Int) {
                (requireActivity() as MainActivity).displayFragment(
                    DetailsFragment.newInstance(
                    imageUrl = viewModel.imagelist.value?.get(position)?.imageUrl.toString(),
                    imageDescription = viewModel.imagelist.value?.get(position)?.description.toString()

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
        recyclerView?.layoutManager = GridLayoutManager(context, 2) //set options for display elements on view
        recyclerView?.adapter = rvAdapter //attach adapter, we can change different adapters for display info in RV
        viewModel.imagelist.value?.let { rvAdapter.setImagePreviewIntoRecyclerView(it)
        }
    }

}