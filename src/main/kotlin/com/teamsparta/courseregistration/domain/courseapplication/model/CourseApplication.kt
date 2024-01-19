package com.teamsparta.courseregistration.domain.courseapplication.model

import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.course.model.toResponse
import com.teamsparta.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.user.model.User
import com.teamsparta.courseregistration.domain.user.model.toResponse
import jakarta.persistence.*

@Entity//엔티티 선언함.
@Table(name = "course_application") //@테이블 (이름= "테이블이름")
class CourseApplication(
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: CourseApplicationStatus = CourseApplicationStatus.PENDING,

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
) {
    @Id //아이디 관련 내용 작성. 이 작성후 다른데서 Id 관련 내용 작성안함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun isProceeded(): Boolean {
        return status != CourseApplicationStatus.PENDING
    }

    fun accept() {
        status = CourseApplicationStatus.ACCEPTED
    }

    fun reject() {
        status = CourseApplicationStatus.REJECTED
    }
}
}
fun CourseApplication.toResponse(): CourseApplicationResponse {
    return CourseApplicationResponse(
        id = id!!,
        course = course.toResponse(),
        user = user.toResponse(),
        status = status.name
    )
}