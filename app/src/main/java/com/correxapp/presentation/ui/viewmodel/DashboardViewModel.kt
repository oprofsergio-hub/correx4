package com.correxapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.correxapp.domain.model.DashboardStats
import com.correxapp.domain.model.ExamModel
import com.correxapp.domain.usecase.GetAllExamsUseCase
import com.correxapp.domain.usecase.GetDashboardStatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardStatsUseCase: GetDashboardStatsUseCase,
    private val getAllExamsUseCase: GetAllExamsUseCase
) : ViewModel() {

    private val _dashboardStats = MutableStateFlow<DashboardStats?>(null)
    val dashboardStats: StateFlow<DashboardStats?> = _dashboardStats.asStateFlow()

    private val _exams = MutableStateFlow<List<ExamModel>>(emptyList())
    val exams: StateFlow<List<ExamModel>> = _exams.asStateFlow()

    // Propriedade para compatibilidade
    val allExams: StateFlow<List<ExamModel>> = _exams.asStateFlow()
}
