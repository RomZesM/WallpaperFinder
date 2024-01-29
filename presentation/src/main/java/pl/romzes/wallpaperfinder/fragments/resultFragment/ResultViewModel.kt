package pl.romzes.wallpaperfinder.fragments.resultFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase

class ResultViewModel() : ViewModel() {
    private val getImageFromApiUseCase = GetImagesFromAPIUseCase(ApiInterfaceImpl())

    val testString : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getImagesFromApi() : List<ImagePreview>{
      return getImageFromApiUseCase.getUseCase()
    }
}