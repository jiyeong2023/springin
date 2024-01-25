package com.teamsparta.courseregistration.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Aspect
@Component
class StopWatchAspect {
    private val logger = LoggerFactory.getLogger("Execution Time Logger")

    @Around("@annotation(com.moveuk.courseregistration.infra.aop.StopWatch)")
    fun run(joinPoint: ProceedingJoinPoint): Any {
        val stopWatch = StopWatch()

        stopWatch.start()
        val proceed = joinPoint.proceed()
        stopWatch.stop()

        val methodName = joinPoint.signature.name
        val methodArguments = joinPoint.args

        val timeElapsedMs = stopWatch.totalTimeMillis
        logger.info("Method Name: $methodName | Arguments: ${methodArguments.joinToString(", ")} Execution Time: ${timeElapsedMs}ms")
        return proceed
    }
}