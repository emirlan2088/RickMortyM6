package com.example.therickandmortybook.data.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.therickandmortybook.data.dataBaseLocal.daos.NoteDao
import com.example.therickandmortybook.data.dataBaseLocal.model.DataModel
import com.example.therickandmortybook.data.datasource.ApiService
import com.example.therickandmortybook.data.datasource.characterPagingSource.CharactersPagingSource
import com.example.therickandmortybook.data.model.charcter.CharacterDto
import com.example.therickandmortybook.data.model.charcter.ResultDto
import com.example.therickandmortybook.data.model.charcter.ResultDto.Companion.toDataModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PagerRepository(
    private val apiService: ApiService,
    private val dao: NoteDao,
    private val io: CoroutineDispatcher,
) {
    fun getCharacters(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null
    ): Flow<PagingData<ResultDto>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { CharactersPagingSource(
                apiService,
                name,
                status,
                species,
                type,
                gender
            )
            }
        ).flow.flowOn(io)
    }

    suspend fun addFavorites(character: ResultDto) {
        dao.insertFavoriteCharacter(character.toDataModel())
    }

    suspend fun deleteFavorites(character: ResultDto) {
        dao.deleteFavoriteCharacter(character.toDataModel())
    }

    fun getFavorites(): Flow<PagingData<DataModel>> {
        return dao.fetchAllFavoriteCharacters()
            .map { list -> PagingData.from(list) }
            .flowOn(io)
    }
}