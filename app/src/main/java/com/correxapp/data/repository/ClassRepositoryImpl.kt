package com.correxapp.data.repository

import com.correxapp.data.source.local.dao.ClassDao
import com.correxapp.domain.model.ClassModel
import com.correxapp.domain.model.ClassWithStudents
import com.correxapp.domain.model.StudentModel
import com.correxapp.domain.repository.ClassRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClassRepositoryImpl @Inject constructor(
    private val classDao: ClassDao
) : ClassRepository {
    override fun getClasses(): Flow<List<ClassWithStudents>> {
        return classDao.getClassesWithStudents()
    }

    override suspend fun addClass(classModel: ClassModel) {
        classDao.insertClass(classModel)
    }

    override fun getClassWithDetails(classId: Long): Flow<ClassWithStudents?> {
        return classDao.getClassWithStudentsById(classId)
    }

    override suspend fun addStudent(student: StudentModel) {
        classDao.insertStudent(student)
    }
}
