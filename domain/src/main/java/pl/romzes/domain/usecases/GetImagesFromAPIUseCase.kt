package pl.romzes.domain.usecases

import pl.romzes.domain.interfaces.ApiInterface
import pl.romzes.domain.model.ImagePreview

class GetImagesFromAPIUseCase (private val repoApi : ApiInterface){


    suspend fun execute(request : String) : List<ImagePreview> {
        return repoApi.getImagesFromUnsplashApi(request)
    }
}