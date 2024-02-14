package pl.romzes.domain.interfaces

import android.content.Context
import pl.romzes.domain.model.ImagePreview

interface DataBaseInterface {

    suspend fun getFavouriteImagesList(context: Context): List<ImagePreview>

    suspend fun saveImage(context: Context, image: ImagePreview)

    suspend fun deleteImage(context: Context, image: ImagePreview)
}
