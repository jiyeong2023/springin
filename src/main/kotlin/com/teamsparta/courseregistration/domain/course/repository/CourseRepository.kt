package com.teamsparta.courseregistration.domain.course.repository

import com.teamsparta.courseregistration.domain.course.model.Course
import org.springframework.data.jpa.repository.JpaRepository
//jpa 리포지토리 상속<해당문서, 문서타입> { } 스프링부트공식문서- jpql 문서 참고하기.
interface CourseRepository: JpaRepository<Course, Long> {

}