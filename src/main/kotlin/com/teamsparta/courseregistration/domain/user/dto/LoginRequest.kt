package com.teamsparta.courseregistration.domain.user.dto

data class SignUpRequest ( //받을 정보 이메일, 패스워드, 닉네임, 역할 받음
    val email: String,
    val password: String,
    val nickname: String,
    val role: String,
)
