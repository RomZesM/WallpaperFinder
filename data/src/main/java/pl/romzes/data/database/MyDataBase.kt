package pl.romzes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.romzes.data.dao.ImagePreviewDao
import pl.romzes.data.models.ImagePreviewEntity
import pl.romzes.domain.model.ImagePreview

@Database(entities = [ImagePreviewEntity::class], version = 2)
abstract class MyDataBase : RoomDatabase(){
    //room will insert this dependency
    abstract fun getDao() : ImagePreviewDao

    //create db on device, or return instance of db if it already was created
    companion object {


        fun initDb (context : Context) : MyDataBase{
            return Room.databaseBuilder(
                context,         //get context main activity
                MyDataBase::class.java,
                "walpaper.db"
            )
            .fallbackToDestructiveMigration() //delete previous db without data saving
            .build()
        }
    }

}