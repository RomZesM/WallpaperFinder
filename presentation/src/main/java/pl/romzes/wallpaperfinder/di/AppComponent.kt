package pl.romzes.wallpaperfinder.di

import dagger.Component
import pl.romzes.wallpaperfinder.fragments.favouriteFragment.FavouriteFragment
import pl.romzes.wallpaperfinder.fragments.resultFragment.ResultFragment

@Component(modules = [ResultFragmentModule::class,
                        FavouriteFragmentModule::class,
                        DataModule::class,
                        DomainModule::class])
interface AppComponent {
    //make dependency injection into ResultFragment
    fun inject(resultFragment : ResultFragment)

    fun inject(favFragment : FavouriteFragment)
}