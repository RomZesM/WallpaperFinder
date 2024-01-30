package pl.romzes.domain.usecases

import pl.romzes.domain.interfaces.ApiInterface
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.model.UnsplashData
import retrofit2.Response
import kotlin.time.measureTime

class GetImagesFromAPIUseCase (private val repoApi : ApiInterface){

    //todo check how it should be named

    suspend fun  getUseCase(request : String) : Response<UnsplashData>{
        return  repoApi.getImagesFromUnsplashApi(request)
    }
}