package com.example.notaviva.data

import kotlinx.coroutines.flow.Flow

/**
 * Capa de repositorio: aísla al ViewModel de los detalles de Room.
 * Facilita además las pruebas unitarias mediante inyección de dependencias.
 */
class CaseRepository(private val db: AppDatabase) {

    // ============================================================
    // CASOS
    // ============================================================

    fun getAllCases(): Flow<List<CaseEntity>> =
        db.caseDao().getAll()

    fun getCaseById(id: Long): Flow<CaseEntity?> =
        db.caseDao().getById(id)

    suspend fun createCase(case: CaseEntity): Long =
        db.caseDao().insert(case)

    suspend fun updateCase(case: CaseEntity) =
        db.caseDao().update(case)

    suspend fun deleteCase(case: CaseEntity) =
        db.caseDao().delete(case)


    // ============================================================
    // ENTREVISTAS
    // ============================================================

    /**
     * Obtiene las entrevistas de un caso específico.
     *
     * Se utiliza en CaseDetailScreen.
     */
    fun getInterviews(caseId: Long): Flow<List<InterviewEntity>> =
        db.interviewDao().getByCaseId(caseId)

    /**
     * Obtiene todas las entrevistas junto con
     * el nombre del caso correspondiente.
     *
     * Se utiliza en HomeScreen.
     */
    fun getAllInterviewsWithCases(): Flow<List<InterviewWithCase>> =
        db.interviewDao().getAllWithCase()

    suspend fun addInterview(interview: InterviewEntity) =
        db.interviewDao().insert(interview)

    suspend fun updateInterview(interview: InterviewEntity) =
        db.interviewDao().update(interview)

    suspend fun deleteInterview(interview: InterviewEntity) =
        db.interviewDao().delete(interview)


    // ============================================================
    // EVIDENCIAS
    // ============================================================

    fun getEvidences(caseId: Long): Flow<List<EvidenceEntity>> =
        db.evidenceDao().getByCaseId(caseId)

    suspend fun addEvidence(evidence: EvidenceEntity) =
        db.evidenceDao().insert(evidence)

    suspend fun updateEvidence(evidence: EvidenceEntity) =
        db.evidenceDao().update(evidence)

    suspend fun deleteEvidence(evidence: EvidenceEntity) =
        db.evidenceDao().delete(evidence)
}