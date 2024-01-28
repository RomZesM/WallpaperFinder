package pl.romzes.domain.usecases

import pl.romzes.domain.interfaces.ApiInterface

class GetImagesFromAPIUseCase (private val repoApi : ApiInterface){

    //todo check how it should be named
    fun getUseCase() : String{
        return repoApi.getImagesFromUnsplash()
    }
}