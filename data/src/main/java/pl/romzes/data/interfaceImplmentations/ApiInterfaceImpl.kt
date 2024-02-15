package pl.romzes.data.interfaceImplmentations

import UnsplashApi
import android.util.Log
import org.json.JSONObject
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.interfaces.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInterfaceImpl : ApiInterface {

    val TAG  = "rmz"

    override suspend fun getImagesFromUnsplashApi(request: String) : List<ImagePreview>{
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
        if (response.isSuccessful){
            response.body()?.results?.forEach {
                val imagePreview = it.toImageView()
                imagePreviewsList.add(imagePreview)
            }
        }
        else {
            Log.e(TAG, "getImagesFromUnsplashApi: couldn't receive data from unsplash api", )
        }
       
       
        
        Log.d(TAG, "getImagesFromUnsplashApi: isSuccess - " + response.isSuccessful )
        Log.d(TAG, "getImagesFromUnsplashApi: body - " + response.body() )
        return imagePreviewsList
    }

}