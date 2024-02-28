package pl.romzes.data.interfaceImplmentations

import pl.romzes.data.api.UnsplashApi
import android.util.Log
import pl.romzes.domain.model.ImagePreview
import pl.romzes.domain.interfaces.ApiInterface

class ApiInterfaceImpl (private val api : UnsplashApi) : ApiInterface {

    val TAG  = "rmz"

    override suspend fun getImagesFromUnsplashApi(request: String) : List<ImagePreview>{

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
            throw Exception("Sorry, connection problem, try again later")
        }
        Log.d(TAG, "getImagesFromUnsplashApi: isSuccess - " + response.isSuccessful )
        return imagePreviewsList
    }
}