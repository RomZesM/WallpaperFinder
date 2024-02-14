package pl.romzes.wallpaperfinder.utils

import android.widget.ImageView
import pl.romzes.domain.model.ImagePreview

interface MyRecyclerViewOnClickListener {

    fun onClick(position: Int)

    fun favOnClick(image : ImagePreview, position : Int)
}