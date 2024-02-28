package pl.romzes.wallpaperfinder.di

import dagger.Module
import dagger.Provides
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {


   @ApplicationScope
   @Provides
    fun provideRetrofit() : Retrofit{
           // val unsplashBaseUrl : String = "https://api.unsplash.com/"
            return  Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create()) //create converter from json into data object
            .build() //build request
    }


    @Provides
    fun providesDataBaseInterfaceImpl() : DataBaseInterfaceImpl{
        return DataBaseInterfaceImpl()
    }

    @Provides
    fun providesApiInterfaceImpl(retrofit: Retrofit) :  ApiInterfaceImpl{
        return  ApiInterfaceImpl(retrofit)
    }



}