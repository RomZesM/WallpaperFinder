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
import pl.romzes.wallpaperfinder.R
import pl.romzes.wallpapers.utils.Connector


class SearchFieldFragment : Fragment() {

    val TAG = "rmz"
    var connector : Connector? = null

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

    override fun onStart() {
        super.onStart()
        buttonInit()
    }

    private fun buttonInit() {
        val button = view?.findViewById<Button>(R.id.search_wall_button_id)
        val textField = view?.findViewById<EditText>(R.id.query_field_id)
        button?.setOnClickListener(View.OnClickListener {
            val textField = textField?.text
            Log.d(TAG, textField.toString())
            connector?.sendResult(7, "text 777")
        })
    }
}