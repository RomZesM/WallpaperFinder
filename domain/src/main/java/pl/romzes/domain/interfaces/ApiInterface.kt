package pl.romzes.domain.interfaces

import pl.romzes.domain.model.ImagePreview

interface ApiInterface {
    fun getImagesFromUnsplash() : List<ImagePreview>
}