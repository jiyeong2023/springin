package com.teamsparta.courseregistration.domain.course.controlier

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseregistration.domain.course.service.CourseService
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

@RequestMapping("/courses")//dto를 인자로 받는게 컨트롤러. 매핑에서는 API 전체 주소 넣음
@RestController //요청은 받을 수 있게됩. 어노테이션 설정으로.
// 튜터는dtos-컨트롤러 먼저 작성합(crud). 콜스, 렉쳐, 어플리케이션 ,유저 순서로 디티오들과 컨트롤러 작성함.(여기까지 웹레이어), 다음에 서비스레이어 작성함.
// 콜스 서비스,서비스 임플 조금 작성후 예외처리 작성함. 후에 트랙센셕 적용. 서비스임플 작성,DB 작성과 인텔리제이, 어플연결, JPA 설명,
// 모델-엔티티 콜스, 렉쳐,유저,어플리케이션 작성.
// 마지막으로 레포지토리작성, 모둘 파일작성.
//DB 테이블 작성방법,
class CourseController(//생성자 주입, 콜스 서비스 주입, 받음.
    private val courseService: CourseService
) {


    @GetMapping()// 여러건을 가져올때 <List<CourseResponse>>를 가져오는데, ResponseEntity로 감싸서 가져온다.
    fun getCourseList(): ResponseEntity<List<CourseResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getAllCourseList())
    }

    @GetMapping("/{courseId}") //@PathVariable: URI 경로 변수 값을 매핑하는데 사용됩. 위의 주소값과 일치시킨다. 단건조회.
    fun getCourse(@PathVariable courseId: Long) :ResponseEntity<CourseResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseById(courseId))
    }

    @PostMapping //@RequestBody: 요청 dto를 표기할때 사용합니다. 클라이언트로 요청받은 Json을 객체로 매핑해줍니다.
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity <CourseResponse>
    //응답을 CourseResponse을 주는데,ResponseEntity로 감싸서 준다

    {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.createCourse(createCourseRequest))
//        return ResponseEntity.status(HttpStatus.CREATED).body(----)
        //@RequestParam: HTTP 요청 파라미터 값을 매핑하는데 사용합니다.
    }

    @PutMapping("/{courseId}")
    fun updateCourse(@PathVariable courseId: Long, @RequestBody updateCourseRequest: UpdateCourseRequest)
    : ResponseEntity<CourseResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    @DeleteMapping("/{courseId}")
    fun deleteCourse(@PathVariable courseId: Long): ResponseEntity<Unit>{
        courseService.deleteCourse(courseId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()//바디 받지않을때 빌드로 작성한다.
   }


//    @ExceptionHandler(ModelNotFoundException::class) //예외처리 컨트롤러 추가함. 다른방식으로 바꿈 글로벌익센션핸들링에 들어감.
//    fun handleModalNotFoundException(e:ModelNotFoundException): ResponseEntity<Unit>{
//         return ResponseEntity
//               .status(HttpStatus.NOT_FOUND)
//               .build()
    }
}
