package com.teamsparta.courseregistration.domain.courseapplication.dto

data class UpdateApplicationStatusRequest (//튜터가 승인 혹은 거절하기 위한정보. 상태정보만 준다.
    val status: String
)
