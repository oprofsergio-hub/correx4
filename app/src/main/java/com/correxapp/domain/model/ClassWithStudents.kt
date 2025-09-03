package com.correxapp.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class ClassWithStudents(
    @Embedded val classInfo: ClassModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "classId"
    )
    val students: List<StudentModel> = emptyList()
)
