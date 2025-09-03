package com.correxapp.domain.usecase

import com.correxapp.domain.model.ExamModel
import com.correxapp.domain.model.StudentResponse
import com.correxapp.domain.repository.ExamRepository
import com.correxapp.domain.repository.StudentResponseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

data class ExamWithResponses(
    val exam: ExamModel,
    val responses: List<StudentResponse>
)

class GetExamWithResponsesUseCase @Inject constructor(
    private val examRepository: ExamRepository,
    private val studentResponseRepository: StudentResponseRepository
) {
    suspend operator fun invoke(examId: Long): Flow<ExamWithResponses?> {
        val exam = examRepository.getExamById(examId) ?: return kotlinx.coroutines.flow.flowOf(null)

        return combine(
            studentResponseRepository.getResponsesForExam(examId)
        ) { responses ->
            ExamWithResponses(exam, responses.filterIsInstance<StudentResponse>())
        }
    }
}
