package com.example.notaviva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewDao {

    @Query("SELECT * FROM interviews WHERE caseId = :caseId ORDER BY id DESC")
    fun getByCaseId(caseId: Long): Flow<List<InterviewEntity>>

    @Insert
    suspend fun insert(interview: InterviewEntity): Long

    @Delete
    suspend fun delete(interview: InterviewEntity)
}
