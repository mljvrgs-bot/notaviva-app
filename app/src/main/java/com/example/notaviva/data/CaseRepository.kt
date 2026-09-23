package com.example.notaviva.data

import kotlinx.coroutines.flow.Flow

/**
 * Capa de repositorio: aísla al ViewModel de los detalles de Room.
 * Facilita además las pruebas unitarias mediante inyección de dependencias.
 */
class CaseRepository(private val db: AppDatabase) {

    // ---- Casos ----
    fun getAllCases(): Flow<List<CaseEntity>> = db.caseDao().getAll()

    fun getCaseById(id: Long): Flow<CaseEntity?> = db.caseDao().getById(id)

    suspend fun createCase(case: CaseEntity): Long = db.caseDao().insert(case)

    suspend fun updateCase(case: CaseEntity) = db.caseDao().update(case)

    suspend fun deleteCase(case: CaseEntity) = db.caseDao().delete(case)

    // ---- Entrevistas ----
    fun getInterviews(caseId: Long): Flow<List<InterviewEntity>> =
        db.interviewDao().getByCaseId(caseId)

    suspend fun addInterview(interview: InterviewEntity) = db.interviewDao().insert(interview)

    suspend fun updateInterview(interview: InterviewEntity) = db.interviewDao().update(interview)

    suspend fun deleteInterview(interview: InterviewEntity) = db.interviewDao().delete(interview)

    // ---- Evidencias ----
    fun getEvidences(caseId: Long): Flow<List<EvidenceEntity>> =
        db.evidenceDao().getByCaseId(caseId)

    suspend fun addEvidence(evidence: EvidenceEntity) = db.evidenceDao().insert(evidence)

    // Nuevo: delega al DAO la actualizacion de una evidencia ya existente.
    suspend fun updateEvidence(evidence: EvidenceEntity) = db.evidenceDao().update(evidence)

    suspend fun deleteEvidence(evidence: EvidenceEntity) = db.evidenceDao().delete(evidence)
}