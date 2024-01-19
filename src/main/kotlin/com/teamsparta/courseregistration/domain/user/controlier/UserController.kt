package com.teamsparta.courseregistration.domain.user.controlier

import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.user.dto.SignUpRequest
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import com.teamsparta.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseregistration.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController //유저 인증과정이 들어가야 하는데, 유저스로 묶이지 않아서 리퀘스트매핑은 하지 않는다. 일단은 사인업만 구현하겠다.
// 튜터는dtos-컨트롤러 먼저 작성합.
class UserController( private val userService: UserService) { //유저서비스 주입함.

    @PostMapping//로그인 구현
    fun signup(@RequestBody signUpRequest: SignUpRequest) : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signup(signUpRequest))
    }

    @PutMapping //로그인 로그아웃 제외 프로파일
    fun updateUserProfile(@PathVariable userId: Long,
                          @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ) : ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUserProfile(userId, updateUserProfileRequest))
    }
}