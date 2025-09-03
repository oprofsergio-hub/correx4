package com.correxapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.correxapp.domain.model.ClassModel
import com.correxapp.domain.model.ClassWithStudents
import com.correxapp.domain.usecase.AddClassUseCase
import com.correxapp.domain.usecase.GetClassesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor(
    getClassesUseCase: GetClassesUseCase,
    private val addClassUseCase: AddClassUseCase
) : ViewModel() {

    // Expõe a lista de turmas como um StateFlow.
    // A UI irá "observar" este Flow para se atualizar.
    val classes: StateFlow<List<ClassWithStudents>> = getClassesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // Começa a coletar quando a UI está visível
            initialValue = emptyList() // Valor inicial enquanto os dados não chegam
        )

    // Função chamada pela UI para criar uma nova turma.
    fun addClass(name: String, subject: String) {
        viewModelScope.launch { // Executa em uma coroutine segura
            try {
                val classModel = ClassModel(name = name, subject = subject.ifBlank { null })
                addClassUseCase(classModel)
            } catch (e: Exception) {
                // Em um app de produção, exporíamos esse erro para a UI (ex: com um Snackbar)
                // Por agora, vamos manter simples.
            }
        }
    }
}