package com.correxapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_responses")
data class StudentResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val examId: Long,
    val studentId: Long,
    val studentName: String,
    val responses: String, // JSON das respostas
    val score: Double,
    val hits: Int, // Acertos
    val correctionDate: Long,
    val processingTimeMs: Long,
    val averageConfidence: Double,
    val individualAnswers: List<String>, // Respostas individuais
    val sheetBitmap: String? = null, // Base64 da imagem
    val createdAt: Long = System.currentTimeMillis()
)
