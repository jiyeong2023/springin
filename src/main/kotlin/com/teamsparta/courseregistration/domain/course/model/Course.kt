package com.teamsparta.courseregistration.domain.course.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.courseapplication.model.CourseApplication
import com.teamsparta.courseregistration.domain.lecture.model.Lecture
import jakarta.persistence.*

@Entity //jpa 영속성 매니저 설명, ORM 설명함. 영속성-데이터베이스저장하고 사용될시 영속성이 있다고 정의함. 엔티티 언급, 캐싱언급 지연로딩 즉시로딩 언급,
@Table(name = "course")//어노테이션으로 테이블 알려줌(네임= "테이블 이름") 더티체킹: 엔티티 변경사항을 추적해서 판단하는 것. 어떤 쿼리 보낼지 결정함.
class Course(//엔티티 매니저: 엔티티를 관리하는 역할. 여려종류의 함수를 지원함. sql에서 CRUD 하는것과 비슷하다.DB에서 파인드를 가져오고, flush()로
    //응답함.  JPA 관련 기능을 직접적으로 사용하지 않고 한번 감싼 리포지토리(JPA리포지토리 상속 <리포지토리종류, 자료형태>
    // { 메소드 fun find--(): }로 사용함.
    @Column(name = "title", nullable = false)//컬럼은 테이블이랑 어떤 매핑이된다 의미(이름= "테이블내이름", 상태= 필요시 )
    var title: String,//어플리케이션에서 쓰는 용어: 타입. 경우따라 위의 파리미터 내 이름과 동일하지 않아도 된다.

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING) //실수방지하기 위해 이넘사용. 이넘사용시 붙이는 어노테이션. 여기서는 0-오픈, 1-클로스 의미함. (이넘타입.스트링)
    @Column(name = "status", nullable = false)
    var status: CourseStatus = CourseStatus.OPEN,

    @Column(name = "max_applicants", nullable = false)
    val maxApplicants: Int = 30,//바뀌지 않는 값 val 지정.

    @Column(name = "num_applicants", nullable = false)
    var numApplicants: Int = 0,//강의신청시 신청인원없음. 0으로 지정

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var lectures: MutableList<Lecture> = mutableListOf(), //미터블리스트- 자료가 변할수 있을때 사용한다. 메트바이는 연관관계아닌쪽에 표시함
    //뱃지= 뱃지타입.성능항상 위해 레이지(지연로딩).

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var courseApplications: MutableList<CourseApplication> = mutableListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //제네레이티벨류
    var id: Long? = null

    fun isFull(): Boolean {
        return numApplicants >= maxApplicants
    }

    fun isClosed(): Boolean {
        return status == CourseStatus.CLOSED
    }

    fun close() {
        status = CourseStatus.CLOSED
    }

    fun addApplicant() {
        numApplicants += 1
    }

    fun addLecture(lecture: Lecture) {
        lectures.add(lecture)
    }

    fun removeLecture(lecture: Lecture) {
        lectures.remove(lecture)
    }

    fun addCourseApplication(courseApplication: CourseApplication) {
        courseApplications.add(courseApplication)
    }

}
fun Course.toResponse(): CourseResponse {
    return CourseResponse(
        id = id!!,
        title = title,
        description = description,
        status = status.name,
        maxApplicants = maxApplicants,
        numApplicants = numApplicants
    )
}