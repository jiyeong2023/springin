package com.teamsparta.courseregistration

import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CourseServiceTest : BehaviorSpec({
	extension(SpringExtension)

	afterContainer {
		clearAllMocks()
	}

	val courseRepository = mockk<CourseRepository>()
	val lectureRepository = mockk<LectureRepository>()
	val courseApplicationRepository = mockk<CourseApplicationRepository>()
	val userRepository = mockk<UserRepository>()

	val courseService =
		CourseServiceImpl(courseRepository, lectureRepository, courseApplicationRepository, userRepository)

	Given("Course 목록이 존재하지 않을 때") {
		When("특정 Course를 요청하면") {
			Then("ModelNotFoundException이 발생해야한다.") {

				// given
				val courseId = 1L
				every { courseRepository.findByIdOrNull(any()) } returns null

				shouldThrow<ModelNotFoundException> {
					println( courseService.getCourseById(courseId))
				}

			}
		}

		When("Course 목록을 요청하면") {
			//Then...
		}
	}
})