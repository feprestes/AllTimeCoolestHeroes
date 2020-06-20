package net.vortex.atch.database.characters

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail_path: String,
    val thumbnail_extension: String
)

//fun List<Character>.asDomainModel(): List<Result> {
//    return map {
//        Result(
//            id = it.id,
//            name = it.name,
//            description = it.description,
//            thumbnail_path = it.thumbnail_path,
//            thumbnail_extension = it.thumbnail_extension)
//    }
//}
//
//fun Result(id: Int, name: String, description: String, thumbnail_path: String, thumbnail_extension: String): Result {
//
//    return Result(id, name, description, thumbnail_path, thumbnail_extension)
//}
