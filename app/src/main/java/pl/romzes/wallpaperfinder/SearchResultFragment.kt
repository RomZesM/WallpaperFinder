package pl.romzes.wallpaperfinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SearchResultFragment : Fragment() {

    var someResultData = "empty"
    val TAG = "rmz"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false)


    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, someResultData.toString() + "result fragment")
    }

}