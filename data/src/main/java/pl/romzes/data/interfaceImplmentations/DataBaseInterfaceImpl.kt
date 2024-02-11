package pl.romzes.data.interfaceImplmentations

import android.content.Context
import android.util.Log
import pl.romzes.data.database.MyDataBase
import pl.romzes.data.models.ImagePreviewEntity
import pl.romzes.domain.interfaces.DataBaseInterface
import pl.romzes.domain.model.ImagePreview

class DataBaseInterfaceImpl : DataBaseInterface{

    val TAG  = "rmz"
//    override suspend fun getFavouriteImagesList(context: Context): List<ImagePreview> {
//        val db = MyDataBase.initDb(context)
//        return db.getDao().getAllImageViews()
//
//    }

    override suspend fun saveImage(context: Context, image: ImagePreview) {
        Log.d(TAG, "saveImage: " + image.imageUrl)
        val db = MyDataBase.initDb(context)
        val imagePrev : ImagePreviewEntity = ImagePreviewEntity(null, image.imageUrl, image.description)
        db.getDao().insertImage(imagePrev);
    }
}