package com.correxapp.domain.model

data class DashboardStats(
    val examCount: Int,
    val totalCorrectedSheets: Int,
    val averageScore: Double,
    val latestResponses: List<StudentResponse>,
    val totalExams: Int = examCount // ← CAMPO QUE ESTAVA FALTANDO
)
