package com.example.notaviva.data

data class InterviewWithCase(
    val id: Long,
    val caseId: Long,
    val personName: String,
    val date: String,
    val findings: String,
    val caseTitle: String
)