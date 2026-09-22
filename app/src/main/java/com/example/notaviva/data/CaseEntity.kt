package com.example.notaviva.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa un caso de investigación periodística.
 */
@Entity(tableName = "cases")
data class CaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val date: String,               // dd/MM/yyyy
    val status: String = CaseStatus.EN_INVESTIGACION.name,
    val conclusion: String = ""
)
