package com.example.notaviva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CaseDao {

    @Query("SELECT * FROM cases ORDER BY id DESC")
    fun getAll(): Flow<List<CaseEntity>>

    @Query("SELECT * FROM cases WHERE id = :caseId")
    fun getById(caseId: Long): Flow<CaseEntity?>

    @Insert
    suspend fun insert(caseEntity: CaseEntity): Long

    @Update
    suspend fun update(caseEntity: CaseEntity)

    @Delete
    suspend fun delete(caseEntity: CaseEntity)
}
