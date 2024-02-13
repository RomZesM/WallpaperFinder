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
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase

class FavouriteViewModel : ViewModel() {
    val TAG = "rmz"
    private val getImagesFromDBUseCase = GetImagesFromDBUseCase(DataBaseInterfaceImpl())
    private val saveFavImageUseCase = SaveFavImageUseCase(DataBaseInterfaceImpl())


    val imagelist : MutableLiveData<List<ImagePreview>> = MutableLiveData<List<ImagePreview>>()


    fun getImagesFromDB(context: Context){
        viewModelScope.launch(Dispatchers.IO){
            val imageListFromDb = getImagesFromDBUseCase.getUseCase(context)

            imagelist.postValue(imageListFromDb)


        }
    }
    fun saveFavouriteImage(context: Context, image: ImagePreview){
        viewModelScope.launch(Dispatchers.IO){
            saveFavImageUseCase.execute(context, image)
        }
    }
}