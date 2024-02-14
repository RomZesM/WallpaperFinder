package pl.romzes.domain.interfaces

import pl.romzes.domain.model.ImagePreview

interface ApiInterface {
    suspend fun getImagesFromUnsplashApi(request: String) : List<ImagePreview>
}