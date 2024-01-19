package com.teamsparta.courseregistration.domain.courseapplication.dto

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.user.dto.UserResponse

data class CourseApplicationResponse (//상태정보만아니라 콜스, 렉쳐 정보 같이 줌으로 여기서 조회가 가능하도록 데이터를 준다.
    val id: Long,
    val course: CourseResponse,
    val user: UserResponse,
    val status: String,
)
