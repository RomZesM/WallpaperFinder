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

    val testString : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    val _response = MutableLiveData<Response<UnsplashData>?>()
    val response : LiveData<Response<UnsplashData>?> = _response
    fun getImagesFromApi(request: String){

        viewModelScope.launch (Dispatchers.IO) {
            val list =  getImageFromApiUseCase.getUseCase(request)
            _response.postValue(list)

            Log.d(TAG, list.toString())
        }
    }

    fun setUserRequest(msg : String) {
        userRequest.value = msg;
    }

    fun getUserRequest() : String {
        return userRequest.value.toString()
    }

}