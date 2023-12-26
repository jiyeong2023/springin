package com.teamsparta.courseregistration.domain.course.service

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseregistration.domain.courseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseregistration.domain.courseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.courseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.courseregistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseregistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseregistration.domain.lecture.dto.UpdateLectureRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CourseServiceImpl : CourseService {

    override fun getAllCourseList(): List<CourseResponse>{
        //TODO: 만약  courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
       //TODO: DB에서 모든 Course 목록을 조회하여 CourseResponse 목록으로 변환후 반환
         TODO("Not yet implemented")
   }

    override fun getCourseById(courseId: Long): CourseResponse{
        //TODO: DB에서 ID기반으로 Course 목록을 가져와서 CourseResponse 목록으로 변환후 반환
        TODO("Not yet implemented")
    }
    @Transactional
    override fun createCourse(request: CreateCourseRequest) : CourseResponse{
        //TODO: request를 Course로 변환후 DB에 저장
        TODO("Not yet implemented")
    }
    @Transactional
    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse{
        //TODO: 만약  courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //TODO: DB에서 courseId에 해당하는 Coursese를 가져와서 request기반으로 업데이트 한 후 DB에 저장, 결과를 CourseResponse로
        // 변환후 반환
        TODO("Not yet implemented")
    }
    @Transactional
    override fun deleteCourse(courseId: Long){
        //TODO: 만약  courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //TODO: DB에서 CourseId에 해당하는 Course를 삭제, 연관된 CourseApplication과  Lecture도 모두 삭제
        TODO("Not yet implemented")
    }
    @Transactional
    override fun getLectureList(courseId: Long): List<LectureResponse> {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course목록을 가져오고, 하위 lecture들을 가져온 다음, LectureResopnse로 변환해서 반환
        TODO("Not yet implemented")
    }
    @Transactional
    override fun getLecture(courseId: Long, lectureId: Long): LectureResponse {
        // TODO: 만약 courseId, lectureId에 해당하는 Lecture가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서 LectureResponse로 변환 후 반환
        TODO("Not yet implemented")
    }
    @Transactional
    override fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId에 해당하는 Course를 가져와서 Lecture를 추가 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환
        TODO("Not yet implemented")
    }
    @Transactional
    override fun updateLecture(courseId: Long, lectureId: Long, request: UpdateLectureRequest): LectureResponse {
        // TODO: 만약 courseId, lectureId에 해당하는 Lecture가 없다면 throw ModelNotFoundException
        /* TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져와서
            request로 업데이트 후 DB에 저장, 결과를을 LectureResponse로 변환 후 반환 */
        TODO("Not yet implemented")
    }
    @Transactional
    override fun removeLecture(courseId: Long, lectureId: Long) {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, lectureId에 해당하는 Lecture를 가져오고, 삭제
        TODO("Not yet implemented")
    }
    @Transactional
    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
        // TODO: 만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
        // TODO: 만약 course가 이미 마감됐다면, throw IllegalStateException
        // TODO: 이미 신청했다면, throw IllegalStateException

        TODO("Not yet implemented")
    }

    override fun getCourseApplication(courseId: Long, applicationId: Long): CourseApplicationResponse {
        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: DB에서 courseId, applicationId에 해당하는 CourseApplication을 가져와서 CourseApplicationResponse로 변환 후 반환
        TODO("Not yet implemented")
    }


    override fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse> {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse {
        // TODO: 만약 courseId, applicationId에 해당하는 CourseApplication이 없다면 throw ModelNotFoundException
        // TODO: 만약 status가 이미 변경된 상태면 throw IllegalStateException
        // TODO: Course의 status가 CLOSED상태 일시 throw IllegalStateException
        // TODO: 승인을 하는 케이스일 경우, course의 numApplicants와 maxApplicants가 동일하면, course의 상태를 CLOSED로 변경
        // TODO: DB에서 courseApplication을 가져오고, status를 request로 업데이트 후 DB에 저장, 결과를 CourseApplicationResponse로 변환 후 반환
        TODO("Not yet implemented")
    }
}