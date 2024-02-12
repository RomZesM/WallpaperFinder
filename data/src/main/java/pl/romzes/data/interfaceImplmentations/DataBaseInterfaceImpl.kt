package pl.romzes.data.interfaceImplmentations

import android.content.Context
import android.util.Log
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
        Log.d(TAG, "saveImage: " + image.imageUrl)
        val db = MyDataBase.initDb(context)
        val imagePrev : ImagePreviewEntity = ImagePreviewEntity(null, image.imageId, image.imageUrl, image.description, image.width, image.height)
        db.getDao().insertImage(imagePrev);
    }
}