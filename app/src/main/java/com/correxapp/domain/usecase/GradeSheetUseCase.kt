package com.correxapp.domain.usecase

import com.correxapp.domain.model.ExamModel
import com.correxapp.domain.model.StudentResponse
import com.correxapp.domain.repository.ExamRepository
import com.correxapp.domain.repository.StudentResponseRepository
import javax.inject.Inject

class GradeSheetUseCase @Inject constructor(
    private val examRepository: ExamRepository,
    private val studentResponseRepository: StudentResponseRepository
) {
    suspend operator fun invoke(
        examId: Long,
        studentResponses: Map<Int, Int>
    ): Result<Double> {
        return try {
            val exam = examRepository.getExamById(examId)
                ?: return Result.failure(Exception("Exam not found"))

            val score = calculateScore(exam, studentResponses)
            val hits = calculateHits(exam, studentResponses)

            val response = StudentResponse(
                examId = examId,
                studentId = 1L,
                studentName = "Student", // ← VALORES TEMPORÁRIOS
                responses = studentResponses.toString(),
                score = score,
                hits = hits,
                correctionDate = System.currentTimeMillis(),
                processingTimeMs = 100L,
                averageConfidence = 0.95,
                individualAnswers = studentResponses.values.map { "Answer $it" }
            )

            studentResponseRepository.insertStudentResponse(response)
            Result.success(score)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun calculateScore(exam: ExamModel, responses: Map<Int, Int>): Double {
        return 85.0 // Placeholder
    }

    private fun calculateHits(exam: ExamModel, responses: Map<Int, Int>): Int {
        return responses.size // Placeholder
    }
}
