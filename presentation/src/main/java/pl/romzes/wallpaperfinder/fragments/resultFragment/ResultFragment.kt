package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import pl.romzes.wallpaperfinder.model.ImagePreview


class ResultFragment : Fragment() {

    var someResultData = "empty"
    val TAG = "rmz"
    val rvAdapter = ImagePreviewRVAdapter()
    //todo get images from Retrofit
    val imageList = listOf<ImagePreview>(
        ImagePreview(R.drawable.android_test, "url", "desription 01"),
        ImagePreview(R.drawable.android_test, "url", "desription 02"),
       ImagePreview(R.drawable.android_test, "url", "desription 03")
    )
    //add a ViewModel, we can use just ResultViewModel(), todo-QA-> what the difference?
    private val resultViewModel : ResultViewModel by viewModels()

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
     ): View? {
        // Inflate the layout for this fragment

     return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

       override fun onStart() {
        super.onStart()
        //
          resultViewModel.testString.value = "Hello! ROmZeS!"


        //
        initRecyclerView()
        Log.d(TAG, someResultData.toString() + "result fragment")
    }

    //init recyclerView on a fragment
    private fun initRecyclerView() {
        //
        Log.d(TAG, resultViewModel.testString.value.toString())
        Log.d(TAG, resultViewModel.testFun())
        //

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_id) //get the recycler view from fragment
        //config recyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2) //set options for diaply elements on view
        recyclerView?.adapter = rvAdapter //attach adapter, we can change different adapters for display info in RV
        rvAdapter.setImagePreviewIntoRecyclerView(imageList)
    }
}