package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
        //Change text in toolbox
        activity?.findViewById<Toolbar>(R.id.toolbar_id)?.title = getString(R.string.result_fragment)


        //TODO remake THIS CHAIN use ASYNC instead
        resultViewModel.imagelistFavourite.observe(this) {
            getImagesFromApi()
        }
        resultViewModel.imagelist.observe(this) {
            initRecyclerView()
            hideProgressBar()
        }
        resultViewModel.error.observe(this){
            showErrorMessage(resultViewModel.error.value)
            hideProgressBar()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
     ): View? {
            //show UP button in ActionBar
         (requireActivity() as MainActivity).showUpButton(true);
        //need to inflate first, to get a swipeToRefreshLayout
        val inflatedView = inflater.inflate(R.layout.fragment_search_result, container, false)

         //init refresh by swipe
        val swipeToRefresh = inflatedView.findViewById<SwipeRefreshLayout>(R.id.search_results_swipe_id)

         swipeToRefresh?.setOnRefreshListener {
             swipeToRefresh.isRefreshing = false;
             getImagesFromApi()
         }

     return inflatedView
    }

    override fun onStart() {
        super.onStart()

        //first get the imageListFrom DB  //todo why do i need a context here?
        context?.let { resultViewModel.getImagesFromDB(it) }
        //getImagesFromApi()  //--here get image on user request

    }

    private fun getImagesFromApi() {
       //todo do not load pages from api, need to use saved list
        var request = "random"

        if(userSearchRequest != null){
            request = userSearchRequest as String
        }
        context?.let { resultViewModel.getImagesFromApi(request, it) }
    }

    //init recyclerView on a fragment
    private fun initRecyclerView() {
       //set myOnclickFunction in adapter
        rvAdapter.setMyOnclickListener(object : MyRecyclerViewOnClickListener{
           //on click we open new fragment with parameters of images
            //todo add some more parameters
            override fun onClick(position: Int) {
                (requireActivity() as MainActivity).displayFragment(DetailsFragment.newInstance(
                    imageUrl = resultViewModel.imagelist.value?.get(position)?.imageUrl.toString(),
                    imageDescription = resultViewModel.imagelist.value?.get(position)?.description.toString()
                ))
            }
            //onclick listener for heart in RV
            override fun favOnClick(image : ImagePreview, position : Int) {
                //save fav image in db //todo can i get context, not context?
                if(!image.isFav){
                    context?.let { resultViewModel.saveFavouriteImage(it, image ) }
                    resultViewModel.imagelist.value?.get(position)?.isFav = true;
                    resultViewModel.imagelist.value?.let {
                        rvAdapter.setImagePreviewIntoRecyclerView(
                            it
                        )
                    }
                }
                else{
                    context?.let { resultViewModel.deleteFavouriteImage(it, image ) }
                    //change fav status and re-display RV
                    resultViewModel.imagelist.value?.get(position)?.isFav = false;
                    resultViewModel.imagelist.value?.let {
                        rvAdapter.setImagePreviewIntoRecyclerView(
                            it
                        )
                    }
                }

            }
        })

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_id) //get the recycler view from fragment
        //config recyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2) //set options for display elements on view
        recyclerView?.adapter = rvAdapter //attach adapter, we can change different adapters for display info in RV
        resultViewModel.imagelist.value?.let { rvAdapter.setImagePreviewIntoRecyclerView(it)
        }
    }

    //show text field with error message if no images was found
    private fun showErrorMessage(message : String?){
        val errorTextView = view?.findViewById<TextView>(R.id.error_text_field_id)
        if(message != null){
            errorTextView?.text = message;
        }
        errorTextView?.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar_rv_id)
        progressBar?.visibility = View.GONE
    }
}