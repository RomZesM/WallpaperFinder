package pl.romzes.wallpaperfinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class SearchFieldFragment : Fragment() {

    val TAG = "rmz"

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
        })
    }
}