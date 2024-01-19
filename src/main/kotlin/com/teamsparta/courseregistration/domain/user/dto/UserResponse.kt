package com.teamsparta.courseregistration.domain.user.dto

data class UserResponse ( //응답- 아이디, 이메일, 닉네임, 역할만 보낸다. 비밀번호는 DB에 저장만 한다.
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
)
