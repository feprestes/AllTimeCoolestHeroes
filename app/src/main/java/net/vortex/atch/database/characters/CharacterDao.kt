package net.vortex.atch.database.characters

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY name ASC")
    fun getAllCharacters(): LiveData<List<Character>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Update
    suspend fun update(character: Character)

    @Delete
    suspend fun delete(character: Character)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()
}