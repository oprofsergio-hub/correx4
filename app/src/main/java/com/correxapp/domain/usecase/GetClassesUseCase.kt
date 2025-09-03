package com.correxapp.domain.usecase

import com.correxapp.domain.model.ClassWithStudents
import com.correxapp.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClassesUseCase @Inject constructor(
    private val repository: ClassRepository
) {
    operator fun invoke(): Flow<List<ClassWithStudents>> {
        return repository.getClasses()
    }
}