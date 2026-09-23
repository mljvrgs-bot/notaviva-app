package com.example.notaviva.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InterviewDao {

    /**
     * Obtiene las entrevistas pertenecientes
     * a un caso específico.
     *
     * Esta función ya era utilizada por CaseDetailScreen,
     * por eso se conserva.
     */
    @Query(
        "SELECT * FROM interviews " +
                "WHERE caseId = :caseId " +
                "ORDER BY id DESC"
    )
    fun getByCaseId(caseId: Long): Flow<List<InterviewEntity>>

    /**
     * Obtiene todas las entrevistas junto con
     * el nombre del caso al que pertenecen.
     *
     * Se utiliza principalmente en HomeScreen.
     */
    @Query(
        """
        SELECT
            i.id AS id,
            i.caseId AS caseId,
            i.personName AS personName,
            i.date AS date,
            i.findings AS findings,
            c.title AS caseTitle
        FROM interviews i
        INNER JOIN cases c
            ON i.caseId = c.id
        ORDER BY i.id DESC
        """
    )
    fun getAllWithCase(): Flow<List<InterviewWithCase>>

    @Insert
    suspend fun insert(interview: InterviewEntity): Long

    @Update
    suspend fun update(interview: InterviewEntity)

    @Delete
    suspend fun delete(interview: InterviewEntity)
}
