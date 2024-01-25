package com.teamsparta.courseregistration.domain.course.repository

import com.teamsparta.courseregistration.domain.course.model.Course
import org.springframework.data.jpa.repository.JpaRepository

class CourseRepository: CustomCourseRepository, QueryDslSupport() {

    private val course = QCourse.course
    private val lecture = QLecture.lecture

    override fun searchCourseListByTitle(title: String): List<Course> {
        return queryFactory.selectFrom(course)
            .where(course.title.containsIgnoreCase(title))
            .fetch()
    }

    override fun findByPageableAndStatus(pageable: Pageable, status: CourseStatus?): Page<Course> {

        val whereClause = BooleanBuilder()
        // 동적으로 where clause 생성
        status?.let { whereClause.and(course.status.eq(status)) }

        // count의 경우 order와 무관하기 때문에 바로 수행
        val totalCount = queryFactory
            .select(course.count())
            .from(course)
            .where(whereClause)
            .fetchOne() ?: 0L

//        // order를 동적으로 설정하기 위해 미리 query를 만들어둠
//        val query = queryFactory.selectFrom(course)
//            .where(whereClause)
//            .offset(pageable.offset)
//            .limit(pageable.pageSize.toLong())
//
//        // order가 존재하는지 확인
//        if (pageable.sort.isSorted) {
//            // 정렬 기준 설정
//            when(pageable.sort.first()?.property) {
//                "id" -> query.orderBy(course.id.asc())
//                "title" -> query.orderBy(course.title.asc())
//                else -> query.orderBy(course.id.asc())
//            }
//        } else {
//            query.orderBy(course.id.asc())
//        }
//
//        // 최종적으로 쿼리 수행
//        val contents = query.fetch()

        val contents = queryFactory.selectFrom(course)
            .where(whereClause)
            .leftJoin(course.lectures, lecture)
//            .fetchJoin() // fetchjoin은 일대다에서 사용하지 않는게 좋음. n * m ....계속 늘어나기 때문에 HIBERNATE에서는 2개 이상 일대다 fetchjoin을 막아놓았음.
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifier(pageable, course))
            .fetch()

        // Page 구현체 반환
        return PageImpl(contents, pageable, totalCount)

    }

    private fun getOrderSpecifier(pageable: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>> {
        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageable.sort.toList().map {
                order -> OrderSpecifier(
            if (order.isAscending) Order.ASC else Order.DESC,
            pathBuilder.get(order.property) as Expression<Comparable<*>>
        )
        }.toTypedArray()
    }
}