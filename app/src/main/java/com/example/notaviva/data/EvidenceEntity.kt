package com.example.notaviva.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Evidencia asociada a un caso (documento, foto, audio, etc.).
 * Se guarda solo la referencia/descripción, no el archivo binario,
 * ya que el ejercicio no requiere backend ni almacenamiento de archivos.
 */
@Entity(
    tableName = "evidences",
    foreignKeys = [
        ForeignKey(
            entity = CaseEntity::class,
            parentColumns = ["id"],
            childColumns = ["caseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("caseId")]
)
data class EvidenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val caseId: Long,
    val name: String,
    val description: String
)
