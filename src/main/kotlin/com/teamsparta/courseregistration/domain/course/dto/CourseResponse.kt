package com.teamsparta.courseregistration.domain.course.dto

import org.springframework.web.bind.annotation.ResponseStatus

data class CourseResponse(//데이터 전달용.뷸변성 유지, 처음으로 만듬
    val id: Long,
    val title: String,
    val description: String?,
    val status: String,
    val maxApplicants:Int,
    val numApplicants:Int,
)






//fun main(args:Array<String>) {
//    val courseResponse = CourseResponse(
//        id = 1L,
//        title = "abc",
//        description = null,
//        status = "CLOSEO",
//        maxApplicants =30,
//        numApplicants = 30
//    )
//    val(id, title, description) = courseResponse
//  println(courseResponse.toString())
//}