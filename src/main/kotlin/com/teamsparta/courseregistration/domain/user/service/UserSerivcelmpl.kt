package com.teamsparta.courseregistration.domain.user.service

import com.teamsparta.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.courseregistration.domain.user.dto.SignUpRequest
import com.teamsparta.courseregistration.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.courseregistration.domain.user.dto.UserResponse
import com.teamsparta.courseregistration.domain.user.model.Profile
import com.teamsparta.courseregistration.domain.user.model.User
import com.teamsparta.courseregistration.domain.user.model.UserRole
import com.teamsparta.courseregistration.domain.user.model.toResponse
import com.teamsparta.courseregistration.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service //스프링에서 찾을수 있도록 서비스 어노테이션 붙여준다
class userServiceImpl( //
    private val userRepository: UserRepository
    private val bCryptPasswordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {


    override fun login(loginRequest: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(loginRequest.email) ?: throw ModelNotFoundException("User", null)

        if (user.role.name != loginRequest.role || !bCryptPasswordEncoder.matches(loginRequest.password, user.password)) {
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = loginRequest.email,
                role = loginRequest.role)
        )
    }
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        // TODO: Email이 중복되는지 확인, 중복된다면 throw IllegalStateException
        // TODO: request를 User로 변환 후 DB에 저장, 비밀번호는 저장시 암호화
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }//이메일이 존재하면 예외 던지고,???

        return userRepository.save(
            User(
                email = request.email,
                // TODO: 비밀번호 암호화
                password = request.password,
                profile = Profile(
                    nickname = request.nickname
                ), //역할에 따라서
                role = when (request.role) {
                    UserRole.STUDENT.name -> UserRole.STUDENT
                    UserRole.TUTOR.name -> UserRole.TUTOR
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        // TODO: 만약 userId에 해당하는 User가 없다면 throw ModelNotFoundException
        // TODO: DB에서 userId에 해당하는 User를 가져와서 updateUserProfileRequest로 업데이트 후 DB에 저장, 결과를 UserResponse로 변환 후 반환

        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }
//튜터님이 뭐라고 설명하는데 모르겠다....
}