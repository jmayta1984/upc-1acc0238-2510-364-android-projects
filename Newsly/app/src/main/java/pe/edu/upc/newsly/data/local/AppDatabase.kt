package pe.edu.upc.newsly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upc.newsly.data.model.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}