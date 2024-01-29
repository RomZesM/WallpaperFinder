package pl.romzes.domain.usecases

import pl.romzes.domain.interfaces.ApiInterface
import pl.romzes.domain.model.ImagePreview

class GetImagesFromAPIUseCase (private val repoApi : ApiInterface){

    //todo check how it should be named
    fun getUseCase(request : String?) : List<ImagePreview>{
        return repoApi.getImagesFromUnsplash(request)
    }
}