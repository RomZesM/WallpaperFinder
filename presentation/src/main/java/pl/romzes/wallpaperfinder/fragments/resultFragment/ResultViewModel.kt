package pl.romzes.wallpaperfinder.fragments.resultFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {

    val testString : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun testFun() : String{
        var string = "Get some data from usecase"
        return string;
    }
}