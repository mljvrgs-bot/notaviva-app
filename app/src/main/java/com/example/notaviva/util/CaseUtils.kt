package com.example.notaviva.util

import com.example.notaviva.data.CaseEntity
import com.example.notaviva.data.CaseStatus

/**
 * Funciones puras de lógica de negocio, separadas de la UI y la persistencia
 * para poder probarlas con JUnit sin necesidad de un dispositivo/emulador.
 */
object CaseUtils {

    /**
     * Filtra una lista de casos por título o descripción (case-insensitive).
     * Una cadena en blanco devuelve la lista completa.
     */
    fun filterCases(cases: List<CaseEntity>, query: String): List<CaseEntity> {
        if (query.isBlank()) return cases
        val normalized = query.trim()
        return cases.filter {
            it.title.contains(normalized, ignoreCase = true) ||
                    it.description.contains(normalized, ignoreCase = true)
        }
    }

    /** Un caso solo se puede publicar/cerrar si tiene al menos una conclusión escrita. */
    fun canClose(case: CaseEntity): Boolean = case.conclusion.isNotBlank()

    /** Valida los campos obligatorios antes de guardar un caso. */
    fun isValidCase(title: String, description: String, date: String): Boolean =
        title.isNotBlank() && description.isNotBlank() && date.isNotBlank()

    fun countByStatus(cases: List<CaseEntity>, status: CaseStatus): Int =
        cases.count { CaseStatus.fromName(it.status) == status }

    // Nuevo: valida los campos obligatorios de una entrevista antes de guardarla.
    // Se exige nombre, fecha y hallazgos porque el registro sin hallazgos
    // no aporta informacion util para el periodista.
    fun isValidInterview(personName: String, date: String, findings: String): Boolean =
        personName.isNotBlank() && date.isNotBlank() && findings.isNotBlank()

    // Nuevo: decision de diseno. Un caso CERRADO ya no admite cambios en su
    // contenido asociado (entrevistas, evidencias). Se centraliza aqui para
    // que la UI y las pruebas usen la misma regla, en vez de repetirla.
    fun canModifyCaseContent(status: CaseStatus): Boolean = status != CaseStatus.CERRADO
}