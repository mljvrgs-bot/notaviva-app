package com.example.notaviva.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notaviva.data.*
import com.example.notaviva.util.CaseUtils
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CaseViewModel(private val repository: CaseRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val allCases: StateFlow<List<CaseEntity>> =
        repository.getAllCases().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredCases: StateFlow<List<CaseEntity>> =
        combine(allCases, _searchQuery) { cases, query -> CaseUtils.filterCases(cases, query) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchChange(query: String) {
        _searchQuery.value = query
    }

    // ---- Casos ----
    fun createCase(title: String, description: String, date: String, status: CaseStatus, onDone: (Long) -> Unit = {}) {
        viewModelScope.launch {
            val id = repository.createCase(
                CaseEntity(title = title, description = description, date = date, status = status.name)
            )
            onDone(id)
        }
    }

    fun updateCase(case: CaseEntity) {
        viewModelScope.launch { repository.updateCase(case) }
    }

    fun deleteCase(case: CaseEntity) {
        viewModelScope.launch { repository.deleteCase(case) }
    }

    fun updateStatus(case: CaseEntity, newStatus: CaseStatus) {
        updateCase(case.copy(status = newStatus.name))
    }

    fun updateConclusion(case: CaseEntity, conclusion: String) {
        updateCase(case.copy(conclusion = conclusion))
    }

    fun getCaseFlow(caseId: Long): StateFlow<CaseEntity?> =
        repository.getCaseById(caseId).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // ---- Entrevistas ----
    fun getInterviews(caseId: Long): StateFlow<List<InterviewEntity>> =
        repository.getInterviews(caseId).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addInterview(caseId: Long, personName: String, date: String, findings: String) {
        viewModelScope.launch {
            repository.addInterview(InterviewEntity(caseId = caseId, personName = personName, date = date, findings = findings))
        }
    }

    // Nuevo: actualiza una entrevista existente. Recibe la entidad completa
    // (normalmente construida con .copy() a partir de la entrevista original)
    // porque Room necesita el id para saber cual fila actualizar.
    fun updateInterview(interview: InterviewEntity) {
        viewModelScope.launch { repository.updateInterview(interview) }
    }

    fun deleteInterview(interview: InterviewEntity) {
        viewModelScope.launch { repository.deleteInterview(interview) }
    }

    // ---- Evidencias ----
    fun getEvidences(caseId: Long): StateFlow<List<EvidenceEntity>> =
        repository.getEvidences(caseId).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addEvidence(caseId: Long, name: String, description: String) {
        viewModelScope.launch {
            repository.addEvidence(EvidenceEntity(caseId = caseId, name = name, description = description))
        }
    }

    fun deleteEvidence(evidence: EvidenceEntity) {
        viewModelScope.launch { repository.deleteEvidence(evidence) }
    }
}

/** Factory manual (sin Hilt/Dagger) para inyectar el repositorio construido a partir del contexto. */
class CaseViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = AppDatabase.getInstance(application)
        val repository = CaseRepository(db)
        @Suppress("UNCHECKED_CAST")
        return CaseViewModel(repository) as T
    }
}