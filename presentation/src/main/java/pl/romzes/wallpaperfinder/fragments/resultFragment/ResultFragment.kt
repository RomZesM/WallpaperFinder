package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.domain.model.ImagePreview
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter


class ResultFragment : Fragment() {

    val TAG = "rmz"
    val rvAdapter = ImagePreviewRVAdapter(this)
    //todo get images from Retrofit

    //add a ViewModel, we can use just ResultViewModel(), todo-QA-> what the difference?
    private val resultViewModel : ResultViewModel = ResultViewModel()
    lateinit var imageList : List<ImagePreview>

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
     ): View? {
        // Inflate the layout for this fragment

     return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

       override fun onStart() {
        super.onStart()
        getImagesFromApi()
        initRecyclerView()
        Log.d(TAG, R.id.image_template_id.toString())
    }

    private fun getImagesFromApi() {
        imageList = resultViewModel.getImagesFromApi()
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