package com.correxapp.domain.repository

import com.correxapp.domain.model.StudentResponse
import kotlinx.coroutines.flow.Flow

interface StudentResponseRepository {
    suspend fun insertStudentResponse(response: StudentResponse)
    fun getResponsesForExam(examId: Long): Flow<List<StudentResponse>>
    suspend fun getTotalCorrectedSheetsCount(): Int
    suspend fun getAverageScore(): Double
    fun getLatestResponses(): Flow<List<StudentResponse>>
}
