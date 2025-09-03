package com.correxapp.data.repository

import com.correxapp.domain.model.StudentResponse
import com.correxapp.domain.repository.StudentResponseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class StudentResponseRepositoryImpl @Inject constructor() : StudentResponseRepository {

    override suspend fun insertStudentResponse(response: StudentResponse) {
        // TODO: Implementar quando tiver DAO
    }

    override fun getResponsesForExam(examId: Long): Flow<List<StudentResponse>> {
        // TODO: Implementar quando tiver DAO
        return flowOf(emptyList())
    }

    override suspend fun getTotalCorrectedSheetsCount(): Int {
        return 0
    }

    override suspend fun getAverageScore(): Double {
        return 0.0
    }

    override fun getLatestResponses(): Flow<List<StudentResponse>> {
        // TODO: Implementar quando tiver DAO
        return flowOf(emptyList())
    }
}
