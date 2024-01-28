package pl.romzes.data.interfaceImplmentations

import pl.romzes.domain.interfaces.ApiInterface

class ApiInterfaceImpl : ApiInterface {
    //todo add retrofit here

    override fun getImagesFromUnsplash(): String {
        return "Data from DATA.API-INTERFACE-IMPLEMENTATION"
    }


}