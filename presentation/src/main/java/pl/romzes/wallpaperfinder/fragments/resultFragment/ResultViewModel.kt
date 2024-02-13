package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase


class ResultViewModel() : ViewModel() {
    val TAG = "rmz"
    private val getImageFromApiUseCase = GetImagesFromAPIUseCase(ApiInterfaceImpl())
    private val getImagesFromDBUseCase = GetImagesFromDBUseCase(DataBaseInterfaceImpl())
    private val saveFavImageUseCase = SaveFavImageUseCase(DataBaseInterfaceImpl())


    //todo -> do not completely understand how it works
   // val _response = MutableLiveData<Response<UnsplashData>?>()
  //  val response : LiveData<Response<UnsplashData>?> = _response

    val imagelist : MutableLiveData<List<ImagePreview>> by lazy{
        MutableLiveData<List<ImagePreview>>()
    }

    val userRequest : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val imagelistFavourite : MutableLiveData<List<ImagePreview>> by lazy{
        MutableLiveData<List<ImagePreview>>()
    }


    fun getImagesFromApi(request: String){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getImageFromApiUseCase.execute(request)
            imagelist.postValue(list)
        }
    }


    fun getImagesFromDB(context: Context){
        viewModelScope.launch(Dispatchers.IO){
          val imagelistFav = getImagesFromDBUseCase.getUseCase(context)
            Log.d(TAG, "getImagesFromDB: " + imagelistFav)
        }

    }

    fun saveFavouriteImage(context: Context, image: ImagePreview){
        viewModelScope.launch(Dispatchers.IO){
            saveFavImageUseCase.execute(context, image)
        }
    }


    fun setUserRequest(msg : String) {
        userRequest.value = msg;
    }

    fun getUserRequest() : String {
        return userRequest.value.toString()
    }

}