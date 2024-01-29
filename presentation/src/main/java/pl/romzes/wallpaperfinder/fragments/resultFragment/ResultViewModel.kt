package pl.romzes.wallpaperfinder.fragments.resultFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase

class ResultViewModel() : ViewModel() {
    private val getImageFromApiUseCase = GetImagesFromAPIUseCase(ApiInterfaceImpl())

    val userRequest  = MutableLiveData<Any>()

    val testString : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getImagesFromApi(request: String?) : List<ImagePreview>{
      return getImageFromApiUseCase.getUseCase(request)
    }

    fun setUserRequest(msg : String) {
        userRequest.value = msg;
    }

    fun getUserRequest() : String {
        return userRequest.value.toString()
    }

}