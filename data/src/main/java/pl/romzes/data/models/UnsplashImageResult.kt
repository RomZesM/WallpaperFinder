package pl.romzes.data.models

import com.google.gson.annotations.SerializedName
import pl.romzes.domain.model.ImagePreview

data class UnsplashImageResult(
    val id : String,
    val width : Int,
    val height: Int,
    @SerializedName("alt_description")
    val altDescription: String,
    val urls : pl.romzes.data.models.ImageUrlList
){
    fun toImageView(): ImagePreview {
        return ImagePreview(
            null,
            id,
            urls.regular,
            altDescription,
            width,
            height
        );
    }
}
