package pl.romzes.wallpaperfinder.fragments.searchFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.fragments.detailsFragment.DetailsViewModel
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragment
import pl.romzes.wallpapers.utils.Connector


class SearchFieldFragment : Fragment() {


    val TAG = "rmz"

    lateinit var viewModel : ViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        buttonInit()
        //show back button
        (requireActivity() as MainActivity).showUpButton(false);
    }

    override fun onStart() {
        super.onStart()

    }

    private fun buttonInit() {
        val button : Button? = view?.findViewById(R.id.search_wall_button_id)
        val textField : EditText?  = view?.findViewById(R.id.query_field_id)

        //todo add message, that only english is able for API
        button?.setOnClickListener(View.OnClickListener {
            (requireActivity() as MainActivity).displayFragment(ResultFragment.newInstance(
                request = textField?.text.toString()
            ))

        })
    }


}