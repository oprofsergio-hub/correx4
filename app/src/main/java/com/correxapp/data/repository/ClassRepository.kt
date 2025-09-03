package com.correxapp.domain.repository

import com.correxapp.domain.model.ClassModel
import com.correxapp.domain.model.ClassWithStudents
import com.correxapp.domain.model.StudentModel
import kotlinx.coroutines.flow.Flow

interface ClassRepository {
    fun getClasses(): Flow<List<ClassWithStudents>>
    suspend fun addClass(classModel: ClassModel)

    fun getClassWithDetails(classId: Long): Flow<ClassWithStudents?>
    suspend fun addStudent(student: StudentModel)
}
