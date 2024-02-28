package pl.romzes.wallpaperfinder.fragments.searchFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import pl.romzes.data.interfaceImplmentations.ApiInterfaceImpl
import pl.romzes.data.interfaceImplmentations.DataBaseInterfaceImpl
import pl.romzes.domain.usecases.DeleteFavImageUseCase
import pl.romzes.domain.usecases.GetImagesFromAPIUseCase
import pl.romzes.domain.usecases.GetImagesFromDBUseCase
import pl.romzes.domain.usecases.SaveFavImageUseCase

class SearchViewModel  : ViewModel(){

    val TAG = "rmz"

}