package pl.romzes.data.interfaceImplmentations

import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.interfaces.ApiInterface

class ApiInterfaceImpl : ApiInterface {
    //todo add retrofit here

    override fun getImagesFromUnsplash(): List<ImagePreview> {
        val imageList = listOf<ImagePreview>(
            ImagePreview(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Flag_of_Madagascar.svg/2560px-Flag_of_Madagascar.svg.png", "desсription 01"),
            ImagePreview(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Flag_of_Poland.svg/2560px-Flag_of_Poland.svg.png", "desсription 02"),
            ImagePreview(1, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Flag_of_Denmark.svg/1920px-Flag_of_Denmark.svg.png", "desсription 03")
        )

        return imageList
    }


}