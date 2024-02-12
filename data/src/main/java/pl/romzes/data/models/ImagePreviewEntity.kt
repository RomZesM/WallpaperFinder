package pl.romzes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.romzes.domain.model.ImagePreview

@Entity(tableName = "imagePreviews")
data class ImagePreviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null, //null as default value, so room generate uniq id for every object
    val unsplashId : String,
    val url : String,
    val description : String,
    val width : Int,
    val height : Int
){
    fun toImageView(): ImagePreview {
        return ImagePreview(
            unsplashId,
            url,
            description,
            width,
            height
        );
    }
}
