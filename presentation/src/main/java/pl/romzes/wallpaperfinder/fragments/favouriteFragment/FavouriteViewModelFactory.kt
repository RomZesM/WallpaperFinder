package pl.romzes.wallpaperfinder.fragments.favouriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase

class FavouriteViewModelFactory(private val getImagesFromDBUseCase : GetImagesFromDBUseCase,
                                private val saveFavImageUseCase : SaveFavImageUseCase,
                                private val deleteFavImageUseCase : DeleteFavImageUseCase) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FavouriteViewModel(getImagesFromDBUseCase,
                                    saveFavImageUseCase,
                                    deleteFavImageUseCase) as T
    }
}