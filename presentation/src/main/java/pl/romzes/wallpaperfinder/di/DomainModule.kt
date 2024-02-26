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
class DomainModule {

    @Provides
    fun provideGetImageFromApiUseCase(interfaceImpl: ApiInterfaceImpl) : GetImagesFromAPIUseCase{
        return GetImagesFromAPIUseCase(interfaceImpl)
    }

    @Provides
    fun provideGetImagesFromDBUseCase(interfaceImpl: DataBaseInterfaceImpl) : GetImagesFromDBUseCase{
      return  GetImagesFromDBUseCase(interfaceImpl)
    }


    @Provides
    fun provideSaveFavImageUseCase(interfaceImpl: DataBaseInterfaceImpl) : SaveFavImageUseCase {
     return SaveFavImageUseCase(interfaceImpl)
    }

    @Provides
    fun provideDeleteFavImageUseCase(interfaceImpl: DataBaseInterfaceImpl) : DeleteFavImageUseCase{
        return DeleteFavImageUseCase(interfaceImpl)
    }
}