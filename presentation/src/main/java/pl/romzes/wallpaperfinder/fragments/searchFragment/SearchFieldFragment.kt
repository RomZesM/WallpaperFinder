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
import pl.romzes.wallpaperfinder.MainActivity
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragment
import pl.romzes.wallpapers.utils.Connector


class SearchFieldFragment : Fragment() {

    val TAG = "rmz"
    var connector : Connector? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.connector = context as Connector
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
        buttonInit()
    }

    override fun onStart() {
        super.onStart()


    }

    private fun buttonInit() {
        val button : Button? = view?.findViewById(R.id.search_wall_button_id)
        val textField : EditText?  = view?.findViewById(R.id.query_field_id)

        button?.setOnClickListener(View.OnClickListener {
            val userRequest = textField?.text.toString()

            //sent value to the resultFragment using FragmentManager
            parentFragmentManager.setFragmentResult("requestKey", bundleOf("Key" to userRequest))

            (requireActivity() as MainActivity).displayFragment(ResultFragment())
        })
    }
}