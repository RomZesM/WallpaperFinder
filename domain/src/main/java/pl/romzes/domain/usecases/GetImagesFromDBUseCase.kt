package pl.romzes.domain.usecases

import android.content.Context
import pl.romzes.domain.interfaces.DataBaseInterface
import pl.romzes.domain.model.ImagePreview

class GetImagesFromDBUseCase(private val dbInterface : DataBaseInterface) {

    suspend fun getUseCase(context: Context) : List<ImagePreview> {
        return dbInterface.getFavouriteImagesList(context)
    }
}