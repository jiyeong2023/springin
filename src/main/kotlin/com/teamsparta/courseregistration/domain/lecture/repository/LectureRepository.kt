package com.teamsparta.courseregistration.domain.lecture.repository

import com.teamsparta.courseregistration.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository: JpaRepository<Lecture, Long> {

    fun findByCourseIdAndId(courseId: Long, lectureId: Long): Lecture?

}
//일대다 관계에서 다쪾에 설정된 엔티티를 추가할때 주의해야 합니다. 아래 코드처럼, 연관관계에 있는 엔티티를 함께 설정을 해줘야 JPA에서
//인지할 수 있습니다.
// lectureRepository.save(
//    lecture(
//         title= title,
//         videourl= videourl,
//         course= course//중요
//         )
//   )
