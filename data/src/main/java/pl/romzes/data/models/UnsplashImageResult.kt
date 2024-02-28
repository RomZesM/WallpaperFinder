package pl.romzes.data.models

import android.util.Log
import com.google.gson.annotations.SerializedName
import pl.romzes.domain.model.ImagePreview

data class UnsplashImageResult(
    val id : String,
    val width : Int,
    val height: Int,
    @SerializedName("alt_description")
    val altDescription: String?,
    val urls : pl.romzes.data.models.ImageUrlList
){
    fun toImageView(): ImagePreview {
        //in case if api return NULL instead of description
        var description : String? = altDescription
        if(description == null){
            description = "Missing image description!"
        }
        return ImagePreview(
            null,
            id,
            urls.regular,
            description,
            width,
            height
        );
    }
}
