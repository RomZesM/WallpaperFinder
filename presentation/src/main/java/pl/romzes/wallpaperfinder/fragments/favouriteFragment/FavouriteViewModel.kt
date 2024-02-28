package pl.romzes.wallpaperfinder.fragments.favouriteFragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase
import javax.inject.Inject

class FavouriteViewModel(private val getImagesFromDBUseCase : GetImagesFromDBUseCase,
                         private val saveFavImageUseCase : SaveFavImageUseCase,
                         private val deleteFavImageUseCase : DeleteFavImageUseCase) : ViewModel() {
    val TAG = "rmz"
//    private val getImagesFromDBUseCase = GetImagesFromDBUseCase(DataBaseInterfaceImpl())
//    private val saveFavImageUseCase = SaveFavImageUseCase(DataBaseInterfaceImpl())
//    private val deleteFavImageUseCase = DeleteFavImageUseCase(DataBaseInterfaceImpl())



    val imagelist : MutableLiveData<MutableList<ImagePreview>> = MutableLiveData<MutableList<ImagePreview>>()


    fun getImagesFromDB(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            val imageListFromDb = getImagesFromDBUseCase.getUseCase(context)
            imagelist.postValue(imageListFromDb.toMutableList())


        }
    }
    fun saveFavouriteImage(context: Context, image: ImagePreview){
        viewModelScope.launch(Dispatchers.IO){
            saveFavImageUseCase.execute(context, image)
        }
    }

    fun deleteFavImageFromDb(context: Context, image: ImagePreview, position: Int){
        viewModelScope.launch(Dispatchers.IO){
            deleteFavImageUseCase.execute(context, image)
        }
        imagelist.value?.removeAt(position)
    }
}