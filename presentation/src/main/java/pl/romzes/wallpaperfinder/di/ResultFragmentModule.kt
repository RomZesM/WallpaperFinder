package pl.romzes.wallpaperfinder.di

import dagger.Module
import dagger.Provides
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase
import pl.romzes.wallpaperfinder.adapters.ImagePreviewRVAdapter
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragmentViewModelFactory

@Module
class ResultFragmentModule {

    @Provides
    fun provideResultFragmentVMFactory(getImagesFromAPIUseCase: GetImagesFromAPIUseCase,
                                       getImagesFromDBUseCase: GetImagesFromDBUseCase,
                                       saveFavImageUseCase: SaveFavImageUseCase,
                                       deleteFavImageUseCase: DeleteFavImageUseCase) : ResultFragmentViewModelFactory{
        return ResultFragmentViewModelFactory(getImagesFromAPIUseCase, getImagesFromDBUseCase, saveFavImageUseCase, deleteFavImageUseCase)
    }

}