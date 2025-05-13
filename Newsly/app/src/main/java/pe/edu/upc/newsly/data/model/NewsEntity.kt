package pe.edu.upc.newsly.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    val title: String,
    val author: String,
    val poster: String,
    val description: String

)
