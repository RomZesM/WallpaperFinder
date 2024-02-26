package pl.romzes.wallpaperfinder.di

import dagger.Module
import dagger.Provides
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase

@Module
class DataModule {

    @Provides
    fun providesDataBaseInterfaceImpl() : DataBaseInterfaceImpl{
        return DataBaseInterfaceImpl()
    }

    @Provides
    fun providesApiInterfaceImpl() :  ApiInterfaceImpl{
        return  ApiInterfaceImpl()
    }


}