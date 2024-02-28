package pl.romzes.wallpaperfinder.di

import dagger.Module
import dagger.Provides
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase
import pl.romzes.wallpaperfinder.fragments.favouriteFragment.FavouriteViewModelFactory

@Module
class FavouriteFragmentModule {

    @Provides
    fun provideFavFragmentVMFactory( getImagesFromDBUseCase : GetImagesFromDBUseCase,
                                     saveFavImageUseCase : SaveFavImageUseCase,
                                     deleteFavImageUseCase : DeleteFavImageUseCase) : FavouriteViewModelFactory{
        return FavouriteViewModelFactory(getImagesFromDBUseCase, saveFavImageUseCase, deleteFavImageUseCase)
    }
}