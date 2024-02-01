package pl.romzes.domain.model

import java.io.Serializable

data class ImagePreview (
    val imageId: Int,
    val imageUrl : String,
    val description : String
) : Serializable