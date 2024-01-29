package pl.romzes.data.interfaceImplmentations

import pl.romzes.data.models.ImagePreview
import pl.romzes.domain.interfaces.ApiInterface

class ApiInterfaceImpl : ApiInterface {
    //todo add retrofit here

    override fun getImagesFromUnsplash(): String {
        val imageList = listOf<ImagePreview>(
            ImagePreview(1, "url", "desсription 01"),
            ImagePreview(1, "url", "desсription 02"),
            ImagePreview(1, "url", "desсription 03")
        )

        return "Data from DATA.API-INTERFACE-IMPLEMENTATION"
    }


}