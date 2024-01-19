package com.teamsparta.courseregistration.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable//이넘사용시 어노테이션 선연
class Profile(
    @Column(name = "nickname", nullable = false) //여기서 있어야할 데이터내용
    var nickname: String,
)