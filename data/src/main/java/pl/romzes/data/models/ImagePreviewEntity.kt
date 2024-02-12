package pl.romzes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.romzes.domain.model.ImagePreview

@Entity(tableName = "imagePreviews")
data class ImagePreviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null, //null as default value, so room generate uniq id for every object
    val url : String,
    val description : String


){
    fun toImageView(): ImagePreview {
        return ImagePreview(
            1,
            url,
            description
        );
    }
}
