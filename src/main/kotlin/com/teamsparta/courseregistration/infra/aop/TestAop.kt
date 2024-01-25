package com.teamsparta.courseregistration.infra.swagger

class TestAop {

    @Around("execution(* com.moveuk.courseregistration.domain.course.service.CourseService.getCourseById(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint) {
        println("AOP START!!!")
        joinPoint.proceed()
        println("AOP END!!!")
    }

}
