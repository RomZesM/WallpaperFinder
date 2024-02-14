package pl.romzes.domain.model

import java.io.Serializable

data class ImagePreview (
    val imageId: String,
    val imageUrl : String,
    val description : String,
    val width: Int,
    val height : Int,
    var isFav : Boolean = false
) : Serializable