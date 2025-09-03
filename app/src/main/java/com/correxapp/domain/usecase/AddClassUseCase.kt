package com.correxapp.domain.usecase

import com.correxapp.domain.model.ClassModel
import com.correxapp.domain.repository.ClassRepository
import javax.inject.Inject

class AddClassUseCase @Inject constructor(
    private val repository: ClassRepository
) {
    suspend operator fun invoke(classModel: ClassModel) {
        repository.addClass(classModel)
    }
}