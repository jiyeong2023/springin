package com.teamsparta.courseregistration.domain.user.model


import jakarta.persistence.Entity
import jakarta.persistence.Table

import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import jakarta.persistence.*



@Entity
@Table(name = "app_user") //클래스 이름과 테이블 이름다르면 (이름 = "테이블이름") 지정해야함.
class User(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Embedded// 엔베디드 종속할때 사용하는 어노테이션
    var profile: Profile,

    @Enumerated(EnumType.STRING)//이넘사용시 어노테이션 선언.
    @Column(name = "role", nullable = false)
    val role: UserRole,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val courseApplications: MutableList<CourseApplication> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = profile.nickname,
        email = email,
        role = role.name
    )
}