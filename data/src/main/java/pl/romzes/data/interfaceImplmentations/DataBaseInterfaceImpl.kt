package pl.romzes.data.interfaceImplmentations

import android.content.Context
import pl.romzes.data.database.MyDataBase
import pl.romzes.data.models.ImagePreviewEntity
import pl.romzes.domain.interfaces.DataBaseInterface
import pl.romzes.domain.model.ImagePreview

class DataBaseInterfaceImpl : DataBaseInterface{

    val TAG  = "rmz"
    override suspend fun getFavouriteImagesList(context: Context): List<ImagePreview> {
        val db = MyDataBase.initDb(context)
        val imagePrevEnt = db.getDao().getAllImageViews();
        val imagePreviewList = mutableListOf<ImagePreview>()
        imagePrevEnt.forEach {
            imagePreviewList.add(it.toImageView())
        }
        return imagePreviewList

    }

    override suspend fun saveImage(context: Context, image: ImagePreview) {
        //TODO add check if such image is in DB
        val db = MyDataBase.initDb(context)
        val imagePrev : ImagePreviewEntity = ImagePreviewEntity(null, image.imageUnsplashId, image.imageUrl, image.description, image.width, image.height)
        db.getDao().insertImage(imagePrev);
    }

    override suspend fun deleteImage(context: Context, image: ImagePreview) {
        val db = MyDataBase.initDb(context)
        db.getDao().deleteImage(image.imageUnsplashId)
    }
}