package com.correxapp.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "students",
    foreignKeys = [ForeignKey(
        entity = ClassModel::class,
        parentColumns = ["id"],
        childColumns = ["classId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class StudentModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val classId: Long
)
