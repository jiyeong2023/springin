package com.teamsparta.courseregistration.domain.user.repository

import com.teamsparta.courseregistration.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
//익시스트바이-무언가를 찾을때 사용하는 함수
    fun findByEmail(email: String): User?

//    @Query("select u from User u where u.email=:email")
//    fun findByEmail(email: String): user?
}