package pl.romzes.wallpaperfinder.fragments.resultFragment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase

class ResultFragmentViewModelFactory(
    private val getImagesFromAPIUseCase: GetImagesFromAPIUseCase,
    private val getImagesFromDBUseCase: GetImagesFromDBUseCase,
    private val saveFavImageUseCase : SaveFavImageUseCase,
    private val deleteFavImageUseCase: DeleteFavImageUseCase) : ViewModelProvider.Factory {

//    private val getImageFromApiUseCase = GetImagesFromAPIUseCase(ApiInterfaceImpl())
//    private val getImagesFromDBUseCase = GetImagesFromDBUseCase(DataBaseInterfaceImpl())
//    private val saveFavImageUseCase = SaveFavImageUseCase(DataBaseInterfaceImpl())
//    private val deleteFavImageUseCase = DeleteFavImageUseCase(DataBaseInterfaceImpl())

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ResultViewModel(getImagesFromAPIUseCase,
                               getImagesFromDBUseCase,
                               saveFavImageUseCase,
                               deleteFavImageUseCase) as T
}
}