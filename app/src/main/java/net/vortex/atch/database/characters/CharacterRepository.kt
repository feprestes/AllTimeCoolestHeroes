package net.vortex.atch.database.characters

import androidx.lifecycle.LiveData

class CharacterRepository (private val characterDao: CharacterDao){
    val allCharacters: LiveData<List<Character>> = characterDao.getAllCharacters()

    suspend fun insert(character: Character) {
        characterDao.insert(character)
    }

    suspend fun update(character: Character){
        characterDao.update(character)
    }

    suspend fun delete(character: Character){
        characterDao.delete(character)
    }

    suspend fun deleteAll(){
        characterDao.deleteAllCharacters()
    }
}