package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.model.UnsplashData
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import retrofit2.Response


class ResultFragment : Fragment() {

    val TAG = "rmz"//todo !del
    val rvAdapter = ImagePreviewRVAdapter(this)

    //add a ViewModel,
    private val resultViewModel : ResultViewModel by viewModels<ResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKey",  requireActivity()) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported.
            val userReq = bundle.getString("Key")
            // put result into ViewModel
            resultViewModel.setUserRequest(userReq.toString())
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

        //listen when list will be updated and start our recycler view.
        resultViewModel.imagelist.observe(this) {
            Log.d(TAG, "Finally - resultlist: " + it.size.toString())
            initRecyclerView()
        }
    }

    private fun getImagesFromApi() {
        resultViewModel.getImagesFromApi(resultViewModel.getUserRequest())
    }

    //init recyclerView on a fragment
    private fun initRecyclerView() {
         val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_id) //get the recycler view from fragment
        //config recyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2) //set options for display elements on view
        recyclerView?.adapter = rvAdapter //attach adapter, we can change different adapters for display info in RV
        resultViewModel.imagelist.value?.let { rvAdapter.setImagePreviewIntoRecyclerView(it) }
    }
}