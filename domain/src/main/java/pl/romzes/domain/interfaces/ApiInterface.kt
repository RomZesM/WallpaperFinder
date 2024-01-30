package pl.romzes.domain.interfaces

import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.model.UnsplashData
import retrofit2.Response

interface ApiInterface {
    fun getImagesFromUnsplashStub(request : String?) : List<ImagePreview>

    suspend fun getImagesFromUnsplashApi() : Response<UnsplashData>

}