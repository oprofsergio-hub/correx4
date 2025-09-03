package com.correxapp.domain.usecase

import com.correxapp.domain.model.ExamModel
import com.correxapp.domain.repository.ExamRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllExamsUseCase @Inject constructor(
    private val examRepository: ExamRepository
) {
    operator fun invoke(): Flow<List<ExamModel>> {
        return examRepository.getAllExams()
    }
}
