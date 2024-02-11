package pl.romzes.domain.usecases

import android.content.Context
import pl.romzes.domain.interfaces.DataBaseInterface
import pl.romzes.domain.model.ImagePreview

class SaveFavImageUseCase(private  val repository : DataBaseInterface) {

    suspend fun execute(context: Context, image : ImagePreview) {
        repository.saveImage(context, image)
    }
}