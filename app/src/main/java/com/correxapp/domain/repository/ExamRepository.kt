package com.correxapp.domain.repository

import com.correxapp.domain.model.ExamModel
import kotlinx.coroutines.flow.Flow

interface ExamRepository {
    suspend fun insertExam(exam: ExamModel) // ← Método que estava faltando
    fun getAllExams(): Flow<List<ExamModel>>
    suspend fun getExamById(id: Long): ExamModel?
    suspend fun getExamCount(): Int // ← Para dashboard stats
    suspend fun deleteExam(examId: Long)
}
