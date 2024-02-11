package pl.romzes.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagePreviews")
data class ImagePreviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null, //null as default value, so room generate uniq id for every object
    val url : String,
    val description : String

)
