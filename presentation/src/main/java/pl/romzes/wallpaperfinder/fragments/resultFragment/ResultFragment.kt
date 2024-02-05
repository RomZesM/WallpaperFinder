package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import pl.romzes.wallpaperfinder.fragments.detailsFragment.DetailsFragment
import pl.romzes.wallpaperfinder.utils.MyRecyclerViewOnClickListener


class ResultFragment : Fragment() {

    val TAG = "rmz"//todo !del
    private val rvAdapter = ImagePreviewRVAdapter(this)
    private var userSearchRequest : String? = null

    //add a ViewModel,
    private val resultViewModel : ResultViewModel by viewModels<ResultViewModel>()


    companion object {
        fun newInstance(request: String) = ResultFragment().apply {
            arguments = bundleOf("userRequest" to request)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //get search request from previous activity through  ARGUMENTS
        userSearchRequest = arguments?.getString("userRequest")


        //listen when list will be updated and start our recycler view.
        resultViewModel.imagelist.observe(this) {
            initRecyclerView()
        }
    }

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
     ): View? {
       // Inflate the layout for this fragment
     return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onStart() {
        super.onStart()
        getImagesFromApi()  //--here get image on user request
    }

    private fun getImagesFromApi() {
       //todo do not load pages from api, need to use saved list
        var request = "random"
        if(userSearchRequest != null){
            request = userSearchRequest as String
        }
        resultViewModel.getImagesFromApi(request)
    }

    //init recyclerView on a fragment
    private fun initRecyclerView() {
       //set myonclickFunction in adapter
        rvAdapter.setMyOnclickListener(object : MyRecyclerViewOnClickListener{
           //on click we open new fragment with parameters of images
            //todo add some more parameters
            override fun onClick(position: Int) {
                (requireActivity() as MainActivity).displayFragment(DetailsFragment.newInstance(
                    imageUrl = resultViewModel.imagelist.value?.get(position)?.imageUrl.toString(),
                    imageDescription = resultViewModel.imagelist.value?.get(position)?.description.toString()

                ))
            }

            override fun favOnClick(image : ImagePreview) {
                Log.d(TAG, "testOnClick: favourite was clicked - " + image.description)
            }
        })

         val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_id) //get the recycler view from fragment
        //config recyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2) //set options for display elements on view
        recyclerView?.adapter = rvAdapter //attach adapter, we can change different adapters for display info in RV
        resultViewModel.imagelist.value?.let { rvAdapter.setImagePreviewIntoRecyclerView(it)
        }
    }
}