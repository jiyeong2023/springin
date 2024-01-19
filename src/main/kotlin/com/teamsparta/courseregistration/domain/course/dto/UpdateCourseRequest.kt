package com.teamsparta.courseregistration.domain.course.dto

data class UpdateCourseRequest(//수정
    val title: String,
    val description: String?
)
