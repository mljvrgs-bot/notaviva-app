package com.example.notaviva

import com.example.notaviva.data.CaseEntity
import com.example.notaviva.data.CaseStatus
import com.example.notaviva.util.CaseUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CaseUtilsTest {

    private val sampleCases = listOf(
        CaseEntity(id = 1, title = "Corrupción en la obra pública", description = "Contratos irregulares", date = "12/03/2025", status = CaseStatus.EN_INVESTIGACION.name),
        CaseEntity(id = 2, title = "Deforestación en la Amazonía", description = "Tala ilegal en reserva natural", date = "05/03/2025", status = CaseStatus.PUBLICADO.name),
        CaseEntity(id = 3, title = "Voces estudiantiles", description = "Educación pública", date = "20/02/2025", status = CaseStatus.CERRADO.name)
    )

    @Test
    fun `filterCases returns all cases when query is blank`() {
        val result = CaseUtils.filterCases(sampleCases, "")
        assertEquals(3, result.size)
    }

    @Test
    fun `filterCases matches by title case-insensitively`() {
        val result = CaseUtils.filterCases(sampleCases, "corrupción")
        assertEquals(1, result.size)
        assertEquals("Corrupción en la obra pública", result.first().title)
    }

    @Test
    fun `filterCases matches by description`() {
        val result = CaseUtils.filterCases(sampleCases, "reserva natural")
        assertEquals(1, result.size)
        assertEquals(2L, result.first().id)
    }

    @Test
    fun `filterCases returns empty list when nothing matches`() {
        val result = CaseUtils.filterCases(sampleCases, "narcotráfico")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `canClose is false when conclusion is blank`() {
        val case = sampleCases[0]
        assertFalse(CaseUtils.canClose(case))
    }

    @Test
    fun `canClose is true when conclusion is present`() {
        val case = sampleCases[0].copy(conclusion = "Se confirmó la irregularidad.")
        assertTrue(CaseUtils.canClose(case))
    }

    @Test
    fun `isValidCase requires title description and date`() {
        assertTrue(CaseUtils.isValidCase("Título", "Descripción", "12/03/2025"))
        assertFalse(CaseUtils.isValidCase("", "Descripción", "12/03/2025"))
        assertFalse(CaseUtils.isValidCase("Título", "", "12/03/2025"))
        assertFalse(CaseUtils.isValidCase("Título", "Descripción", ""))
    }

    @Test
    fun `countByStatus counts correctly per status`() {
        assertEquals(1, CaseUtils.countByStatus(sampleCases, CaseStatus.EN_INVESTIGACION))
        assertEquals(1, CaseUtils.countByStatus(sampleCases, CaseStatus.PUBLICADO))
        assertEquals(1, CaseUtils.countByStatus(sampleCases, CaseStatus.CERRADO))
    }

    @Test
    fun `CaseStatus fromName defaults to EN_INVESTIGACION for unknown values`() {
        assertEquals(CaseStatus.EN_INVESTIGACION, CaseStatus.fromName("VALOR_INVALIDO"))
        assertEquals(CaseStatus.PUBLICADO, CaseStatus.fromName("PUBLICADO"))
    }

    // ---- Entrevistas: validaciones ----

    @Test
    fun `isValidInterview requires person name date and findings`() {
        assertTrue(CaseUtils.isValidInterview("Juan Pérez", "10/03/2025", "Confirmó el hallazgo"))
        assertFalse(CaseUtils.isValidInterview("", "10/03/2025", "Confirmó el hallazgo"))
        assertFalse(CaseUtils.isValidInterview("Juan Pérez", "", "Confirmó el hallazgo"))
        assertFalse(CaseUtils.isValidInterview("Juan Pérez", "10/03/2025", ""))
    }

    @Test
    fun `isValidInterview rejects blank-only values`() {
        // Cadenas de solo espacios deben tratarse como vacias, no como datos validos.
        assertFalse(CaseUtils.isValidInterview("   ", "10/03/2025", "hallazgo"))
    }

    // ---- Estados: restriccion de caso cerrado ----

    @Test
    fun `canModifyCaseContent is true while case is not closed`() {
        assertTrue(CaseUtils.canModifyCaseContent(CaseStatus.EN_INVESTIGACION))
        assertTrue(CaseUtils.canModifyCaseContent(CaseStatus.PUBLICADO))
    }

    @Test
    fun `canModifyCaseContent is false once case is closed`() {
        assertFalse(CaseUtils.canModifyCaseContent(CaseStatus.CERRADO))
    }

    // ---- Cierre del caso: transicion de estado ----

    @Test
    fun `canChangeStatusTo blocks closing without a conclusion`() {
        val caseWithoutConclusion = sampleCases[0]
        assertFalse(CaseUtils.canChangeStatusTo(caseWithoutConclusion, CaseStatus.CERRADO))
    }

    @Test
    fun `canChangeStatusTo allows closing once conclusion is written`() {
        val caseWithConclusion = sampleCases[0].copy(conclusion = "Se confirmó la irregularidad.")
        assertTrue(CaseUtils.canChangeStatusTo(caseWithConclusion, CaseStatus.CERRADO))
    }

    @Test
    fun `canChangeStatusTo allows any transition that is not closing`() {
        val caseWithoutConclusion = sampleCases[0]
        assertTrue(CaseUtils.canChangeStatusTo(caseWithoutConclusion, CaseStatus.EN_INVESTIGACION))
        assertTrue(CaseUtils.canChangeStatusTo(caseWithoutConclusion, CaseStatus.PUBLICADO))
    }

    // ---- Evidencias: validaciones ----

    @Test
    fun `isValidEvidence requires name and description`() {
        assertTrue(CaseUtils.isValidEvidence("Factura #123", "Contrato con sobrecosto"))
        assertFalse(CaseUtils.isValidEvidence("", "Contrato con sobrecosto"))
        assertFalse(CaseUtils.isValidEvidence("Factura #123", ""))
    }

    @Test
    fun `isValidEvidence rejects blank-only values`() {
        assertFalse(CaseUtils.isValidEvidence("   ", "descripcion"))
    }
}