package pl.romzes.domain.model

import com.google.gson.annotations.SerializedName
import pl.romzes.domain.model.ImageUrlList

data class UnsplashImageResult(
    val id : String,
    val width : Int,
    val height: Int,
    @SerializedName("alt_description")
    val altDescription: String,
    val urls : ImageUrlList
)
