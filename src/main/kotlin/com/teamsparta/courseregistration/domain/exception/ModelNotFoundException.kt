package com.teamsparta.courseregistration.domain.exception

data class ModelNotFoundException(val modelName: String, val id: Long ): RuntimeException(
   "Model $modelName not found with given id: $id"
)//자바의 런타임입센션을 상속받아 사용한다. 어플리케이션이 쓸때 사용되는 예외다는 의미.  (공통된 모델네임, 아이디): 런타임입센션 (메시지작성)
//예외처리하는부분은 정확히 말하면 웹레이어어 속한다.