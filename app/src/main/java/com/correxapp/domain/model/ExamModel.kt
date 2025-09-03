package com.correxapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class ExamModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val subject: String? = null,
    val totalQuestions: Int,
    val alternativesPerQuestion: Int = 5,
    val answerKey: String,
    val createdAt: Long = System.currentTimeMillis(),
    val creationDate: Long = System.currentTimeMillis() // ← CAMPO QUE ESTAVA FALTANDO
)
