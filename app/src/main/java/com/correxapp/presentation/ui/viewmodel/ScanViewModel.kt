package com.correxapp.presentation.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.correxapp.domain.model.StudentResponse
import com.correxapp.domain.usecase.GradeSheetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ScanUiState(
    val isProcessing: Boolean = false,
    val result: StudentResponse? = null,
    val error: String? = null,
    val instruction: String = "Aponte para o cartão-resposta"
)

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val gradeSheetUseCase: GradeSheetUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val examId: Long = savedStateHandle.get<Long>("examId") ?: 1L
    private var processingJob: Job? = null

    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState = _uiState.asStateFlow()

    fun onImageCaptured(bitmap: Bitmap) {
        if (processingJob?.isActive == true) return

        processingJob = viewModelScope.launch {
            _uiState.update { it.copy(isProcessing = true, instruction = "Processando...") }

            try {
                // Simular respostas do estudante (Map<Int, Int>)
                val studentResponses = mapOf(1 to 0, 2 to 1, 3 to 2, 4 to 0, 5 to 3)

                // Chamar o use case
                val result = gradeSheetUseCase(examId, studentResponses)

                result.fold(
                    onSuccess = { score ->
                        // Criar StudentResponse com os dados
                        val studentResponse = StudentResponse(
                            id = 0L,
                            examId = examId,
                            studentId = 1L,
                            studentName = "Estudante Teste",
                            responses = studentResponses.toString(),
                            score = score,
                            hits = 8,
                            correctionDate = System.currentTimeMillis(),
                            processingTimeMs = 150L,
                            averageConfidence = 0.95,
                            individualAnswers = listOf("A", "B", "C", "D", "E")
                        )

                        _uiState.update {
                            it.copy(
                                isProcessing = false,
                                result = studentResponse,
                                instruction = "Correção concluída!"
                            )
                        }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                isProcessing = false,
                                error = error.message ?: "Erro desconhecido",
                                instruction = "Tente novamente"
                            )
                        }
                        delay(2000)
                        _uiState.update { it.copy(error = null) }
                    }
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isProcessing = false,
                        error = e.message ?: "Erro na correção",
                        instruction = "Tente novamente"
                    )
                }
                delay(2000)
                _uiState.update { it.copy(error = null) }
            }
        }
    }

    fun resetScan() {
        processingJob?.cancel()
        _uiState.update {
            ScanUiState(instruction = "Aponte para o cartão-resposta")
        }
    }
}
