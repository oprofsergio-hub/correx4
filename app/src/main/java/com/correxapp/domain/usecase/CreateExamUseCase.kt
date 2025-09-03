package com.correxapp.domain.usecase

import com.correxapp.domain.model.ExamModel
import com.correxapp.domain.repository.ExamRepository
import javax.inject.Inject

class CreateExamUseCase @Inject constructor(
    private val examRepository: ExamRepository
) {
    suspend operator fun invoke(exam: ExamModel): Result<Long> {
        return try {
            examRepository.insertExam(exam)
            Result.success(1L) // Temporário - retorna 1L como ID
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
