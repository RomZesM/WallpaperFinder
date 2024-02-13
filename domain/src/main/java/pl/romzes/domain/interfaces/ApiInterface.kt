package pl.romzes.domain.interfaces

import pl.romzes.domain.model.ImagePreview

interface ApiInterface {
    fun getImagesFromUnsplashStub(request : String?) : List<ImagePreview>

    suspend fun getImagesFromUnsplashApi(request: String) : List<ImagePreview>
}