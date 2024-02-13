package pl.romzes.data.interfaceImplmentations

import UnsplashApi
import android.util.Log
import pl.romzes.domain.model.UnsplashData
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.interfaces.ApiInterface
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInterfaceImpl : ApiInterface {

    val TAG  = "rmz"

    override fun getImagesFromUnsplashStub(request : String?): List<ImagePreview> {
        val imageList = listOf<ImagePreview>(
            ImagePreview("1", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Flag_of_Madagascar.svg/2560px-Flag_of_Madagascar.svg.png", "desсription 01", 0, 0),
            ImagePreview("1", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Flag_of_Poland.svg/2560px-Flag_of_Poland.svg.png", "desсription 02", 0,0),
            ImagePreview("1", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Flag_of_Denmark.svg/1920px-Flag_of_Denmark.svg.png", "desсription 03", 0 ,0)
        )
        Log.d(TAG, "Request from Interface: Implementation: " + request.toString())
        return imageList
    }

    override suspend fun getImagesFromUnsplashApi(request: String) : Response<UnsplashData>{
        val unsplashBaseUrl : String = "https://api.unsplash.com/"
        //todo make in separate file
        val api = Retrofit.Builder()
            .baseUrl(unsplashBaseUrl)
            .addConverterFactory(GsonConverterFactory.create()) //create converter from json into data object
            .build() //build request
            .create(UnsplashApi::class.java) //in my api class i need to realize api endpoints

        //hardcode adress
        //val response = api.searchImage().execute();
        //dynamic request - photos?query=autumn&per_page=30&orientation=landscape&client_id=Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc
        var response = api.searchImageWithParams(request, "30", "landscape", "Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc").execute()
        Log.d(TAG, "Response from the Interface: [" + request + "] : " + response)
        return response
    }

    override suspend fun getImagesFromUnsplashApi2(request: String) : List<ImagePreview>{
        val unsplashBaseUrl : String = "https://api.unsplash.com/"
        //todo make in separate file
        val api = Retrofit.Builder()
            .baseUrl(unsplashBaseUrl)
            .addConverterFactory(GsonConverterFactory.create()) //create converter from json into data object
            .build() //build request
            .create(UnsplashApi::class.java) //in my api class i need to realize api endpoints


        //val response = api.searchImage().execute();
        //dynamic request - photos?query=autumn&per_page=30&orientation=landscape&client_id=Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc
        var response = api.searchImageWithParams(request, "30", "landscape", "Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc").execute()
        var imagePreviewsList = mutableListOf<ImagePreview>()
        response.body()?.results?.forEach {
            val imagePreview = it.toImageView()
            imagePreviewsList.add(imagePreview)
        }
        return imagePreviewsList
    }

}