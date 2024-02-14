package pl.romzes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.romzes.data.models.ImagePreviewEntity
import pl.romzes.domain.model.ImagePreview

@Dao
interface ImagePreviewDao {
    @Insert
    suspend fun insertImage(image : ImagePreviewEntity)

    @Query ("SELECT * FROM imagePreviews")
    suspend fun getAllImageViews() : List<ImagePreviewEntity>
    @Query ("DELETE FROM imagePreviews WHERE unsplashId = :unsplashId")
    suspend fun deleteImage(unsplashId : String)
}