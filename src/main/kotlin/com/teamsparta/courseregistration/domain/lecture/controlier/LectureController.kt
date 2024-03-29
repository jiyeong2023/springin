package com.teamsparta.courseregistration.domain.lecture.controlier

import com.teamsparta.courseregistration.domain.course.service.CourseService
import com.teamsparta.courseregistration.domain.lecture.dto.AddLectureRequest

import com.teamsparta.courseregistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseregistration.domain.lecture.dto.UpdateLectureRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/courses/{courseid}/lectures")
@RestController
class LectureController ( //콜스 서비스 주입받음. 반환함.
    private val courseService: CourseService
){
     @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
     @GetMapping
     fun getLectureList(@PathVariable courseId: Long): ResponseEntity<List<LectureResponse>> {
         return ResponseEntity
             .status(HttpStatus.OK)
             .body(courseService.getLectureList(courseId))
     }
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    @GetMapping("/{lectureId}")
     fun getLecture(@PathVariable courseId: Long, @PathVariable lectureId: Long): ResponseEntity<LectureResponse>
     //()파라미터 안에 왜 @PathVariable이 두번 들어간 이유: 테이블 연관관계따라서 id를 두개 받은걸까?
     {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getLecture(courseId, lectureId))
     }
    @PreAuthorize("hasRole('TUTOR')")
    @PostMapping
    fun addLecture(
        @PathVariable courseId: Long,
        @RequestBody addLectureRequest: AddLectureRequest
    ) //렉쳐가 어떤 데이터로 생성이 됬는지, 리스폰스로 보내야 되니까 ResponseEntity에 리스폰스 타입을 지정해서 리턴을 해줍니다.
    : ResponseEntity<LectureResponse>{
         return ResponseEntity
             .status(HttpStatus.OK)
             .body(courseService.addLecture(courseId, addLectureRequest))
    }
    @PreAuthorize("hasRole('TUTOR')")
    @PutMapping ("/{lecturId}")
    fun updateLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureId: Long,
        @RequestBody UpdateLectureRequest: UpdateLectureRequest
    ):ResponseEntity<LectureResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateLecture(courseId, lectureId, UpdateLectureRequest))
    }
    @PreAuthorize("hasRole('TUTOR')")
    @DeleteMapping("/{lectureId}")
    fun removeLecture(
        @PathVariable courseId: Long,
        @PathVariable lectureId: Long,
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}