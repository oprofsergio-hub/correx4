package com.correxapp.data.repository

import com.correxapp.domain.model.ExamModel
import com.correxapp.domain.repository.ExamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor() : ExamRepository {

    override suspend fun insertExam(exam: ExamModel) {
        // TODO: Implementar quando tiver DAO
    }

    override fun getAllExams(): Flow<List<ExamModel>> {
        // TODO: Implementar quando tiver DAO
        return flowOf(emptyList())
    }

    override suspend fun getExamById(id: Long): ExamModel? {
        // TODO: Implementar quando tiver DAO
        return null
    }

    override suspend fun getExamCount(): Int {
        // TODO: Implementar quando tiver DAO
        return 0
    }

    override suspend fun deleteExam(examId: Long) {
        // TODO: Implementar quando tiver DAO
    }
}
