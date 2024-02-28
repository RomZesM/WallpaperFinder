package pl.romzes.wallpaperfinder.di

import pl.romzes.data.api.UnsplashApi
import dagger.Module
import dagger.Provides
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    private val retrofit : Retrofit by lazy{
           // val unsplashBaseUrl : String = "https://api.unsplash.com/"
    Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create()) //create converter from json into data object
            .build() //build request
    }

    @Provides
    @ApplicationScope
    fun  provideUnsplashApi() : UnsplashApi {
        return  retrofit.create(UnsplashApi::class.java)
    }

    @Provides
    fun providesDataBaseInterfaceImpl() : DataBaseInterfaceImpl{
        return DataBaseInterfaceImpl()
    }

    @Provides
    fun providesApiInterfaceImpl(api : UnsplashApi) :  ApiInterfaceImpl{
        return  ApiInterfaceImpl(api)
    }



}