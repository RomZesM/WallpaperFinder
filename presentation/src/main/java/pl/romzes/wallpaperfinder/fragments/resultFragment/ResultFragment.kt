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

    val TAG = "rmz"
    val rvAdapter = ImagePreviewRVAdapter(this)
    //todo get images from Retrofit

    //add a ViewModel, we can use just ResultViewModel(), todo-QA-> what the difference?
    private val resultViewModel : ResultViewModel by viewModels<ResultViewModel>()

   // lateinit var imageList : List<ImagePreview>
    val imageList = listOf<ImagePreview>(
        ImagePreview(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Flag_of_Madagascar.svg/2560px-Flag_of_Madagascar.svg.png", "desсription 01"),
        ImagePreview(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Flag_of_Poland.svg/2560px-Flag_of_Poland.svg.png", "desсription 02"),
        ImagePreview(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Flag_of_Denmark.svg/1920px-Flag_of_Denmark.svg.png", "desсription 03"))

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
        initRecyclerView()
        //observe data, wait for it updating
        resultViewModel.response.observe(this) {responce ->
            Log.d(TAG, "From resul fragment" + responce.toString())
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
        rvAdapter.setImagePreviewIntoRecyclerView(imageList)
    }
}