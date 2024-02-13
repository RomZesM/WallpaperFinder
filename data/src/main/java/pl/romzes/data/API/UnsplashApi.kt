import pl.romzes.data.models.UnsplashData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    //"https://api.unsplash.com/search/photos?query=autumn&per_page=30&orientation=landscape&client_id=Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc"


    @GET("search/photos?query=autumn&per_page=30&orientation=landscape&client_id=Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc")
    fun searchImage() : Call<UnsplashData>


    @GET("search/photos?query=autumn&per_page=30&orientation=landscape&client_id=Abed2b9A8CYciNLGC3Ilzfwkw9Lh4-aINn6yKl7ZOxc")
    fun searchImageWithParams(@Query("query") searchQuery : String,
                              @Query("per_page") perPage : String,
                              @Query("orientation") orientation : String,
                              @Query("client_id") clientId : String) : Call<UnsplashData>
}