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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import pl.romzes.wallpaperfinder.app.App
import pl.romzes.wallpaperfinder.fragments.detailsFragment.DetailsFragment
import pl.romzes.wallpaperfinder.utils.MyRecyclerViewOnClickListener
import javax.inject.Inject


class ResultFragment : Fragment() {

    val TAG = "rmz"//todo !del
    private val rvAdapter = ImagePreviewRVAdapter(this)
    private var spanCountInRV = 2;
    private var userSearchRequest : String? = null
    private var imageListWasLoaded : Boolean = false;


    @Inject
    lateinit var resultFragmentVMFactory : ResultFragmentViewModelFactory

    private lateinit var resultViewModel : ResultViewModel

    companion object {
        fun newInstance(request: String) = ResultFragment().apply {
            arguments = bundleOf("userRequest" to request)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //makeInjection?? from App for all fields with @inject annotation
        (activity?.applicationContext as App).appComponent.inject(this)

        //init viwModel with customFaсtory
        resultViewModel = ViewModelProvider(this, resultFragmentVMFactory).get(ResultViewModel::class.java)

        //get search request from previous activity through  ARGUMENTS
        userSearchRequest = arguments?.getString("userRequest")



        //TODO remake THIS CHAIN use ASYNC instead
        resultViewModel.imagelistFavourite.observe(this) {
            getImagesFromApi()
        }
        resultViewModel.imagelist.observe(this) {
            initRecyclerView()
            hideProgressBar()
            //todo-remove? kostyl'
            hideErrorMessage()
            imageListWasLoaded = true
        }
        resultViewModel.error.observe(this){
            showErrorMessage(resultViewModel.error.value)
            hideProgressBar()
        }

        //first get the imageListFrom DB
        if(savedInstanceState == null){
            context?.let { resultViewModel.getImagesFromDB(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
     ): View? {

        //Change text in toolbox
        (requireActivity() as MainActivity).findViewById<Toolbar>(R.id.toolbar_id)?.title = getString(R.string.result_fragment)
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
        //getImagesFromApi()  //--here get image on user request
        spanCountInRV = if(this.resources.configuration.orientation == 1){
            2
        } else
            5
        //try to avoid
        if(imageListWasLoaded){
            initRecyclerView()
            hideProgressBar()
        }
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
                val imagePreview = resultViewModel.imagelist.value?.get(position)

                (requireActivity() as MainActivity).displayFragment(DetailsFragment.newInstance(
                    imageUrl = imagePreview?.imageUrl.toString(),
                    imageDescription = imagePreview?.description.toString(),
                    width =  imagePreview?.width.toString(),
                    height =  imagePreview?.height.toString()
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
        recyclerView?.layoutManager = GridLayoutManager(context, spanCountInRV) //set options for display elements on view
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

    private fun hideErrorMessage(){
        val errorTextView = view?.findViewById<TextView>(R.id.error_text_field_id)
        errorTextView?.visibility = View.GONE
    }

    private fun hideProgressBar(){
        val progressBar = view?.findViewById<ProgressBar>(R.id.progressBar_rv_id)
        progressBar?.visibility = View.GONE
    }
}