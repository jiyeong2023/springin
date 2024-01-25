package com.teamsparta.courseregistration.infra.swagger

@Configuration
class PasswordEncoderConfig {
    @Bean
    fun PasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
