package pl.romzes.wallpaperfinder.fragments.resultFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.model.UnsplashData
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import retrofit2.Response


class ResultViewModel() : ViewModel() {
    val TAG = "rmz"
    private val getImageFromApiUseCase = GetImagesFromAPIUseCase(ApiInterfaceImpl())

    val userRequest  = MutableLiveData<Any>()

    //todo -> do not completely understand how it works
    val _response = MutableLiveData<Response<UnsplashData>?>()
    val response : LiveData<Response<UnsplashData>?> = _response

    val imagelist : MutableLiveData<List<ImagePreview>> by lazy{
        MutableLiveData<List<ImagePreview>>()
    }

    fun getImagesFromApi(request: String){
        viewModelScope.launch (Dispatchers.IO) {
            val unsplashResponse =  getImageFromApiUseCase.getUseCase(request)
            _response.postValue(unsplashResponse)

            Log.d(TAG, "From getImageApi: " +  unsplashResponse.toString())
           // convertResponse()
            val list = mutableListOf<ImagePreview>()
          unsplashResponse.body()?.results?.forEach {
            val preview = ImagePreview(1, it.urls.regular, it.altDescription )
            list.add(preview)
        }
        imagelist.postValue(list)

        }
    }

    fun setUserRequest(msg : String) {
        userRequest.value = msg;
    }

    fun getUserRequest() : String {
        return userRequest.value.toString()
    }

}