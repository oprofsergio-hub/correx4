package com.correxapp.data.database.dao


import androidx.room.*
import com.correxapp.domain.model.ClassModel
import com.correxapp.domain.model.ClassWithStudents
import com.correxapp.domain.model.StudentModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(classModel: ClassModel)

    @Transaction
    @Query("SELECT * FROM classes")
    fun getClassesWithStudents(): Flow<List<ClassWithStudents>>

    @Transaction
    @Query("SELECT * FROM classes WHERE id = :classId")
    fun getClassWithStudentsById(classId: Long): Flow<ClassWithStudents?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentModel)
}
