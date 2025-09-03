package com.correxapp.domain.usecase

import com.correxapp.domain.model.DashboardStats
import com.correxapp.domain.model.StudentResponse
import com.correxapp.domain.repository.ExamRepository
import com.correxapp.domain.repository.StudentResponseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetDashboardStatsUseCase @Inject constructor(
    private val examRepository: ExamRepository,
    private val studentResponseRepository: StudentResponseRepository
) {
    suspend operator fun invoke(): Flow<DashboardStats> {
        return combine(
            studentResponseRepository.getLatestResponses()
        ) { latestResponsesArray ->
            val latestResponses = latestResponsesArray[0].filterIsInstance<StudentResponse>()
            DashboardStats(
                examCount = examRepository.getExamCount(),
                totalCorrectedSheets = studentResponseRepository.getTotalCorrectedSheetsCount(),
                averageScore = studentResponseRepository.getAverageScore(),
                latestResponses = latestResponses
            )
        }
    }
}
