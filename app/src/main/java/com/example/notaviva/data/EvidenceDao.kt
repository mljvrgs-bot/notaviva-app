package com.example.notaviva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EvidenceDao {

    @Query("SELECT * FROM evidences WHERE caseId = :caseId ORDER BY id DESC")
    fun getByCaseId(caseId: Long): Flow<List<EvidenceEntity>>

    @Insert
    suspend fun insert(evidence: EvidenceEntity): Long

    @Delete
    suspend fun delete(evidence: EvidenceEntity)
}
