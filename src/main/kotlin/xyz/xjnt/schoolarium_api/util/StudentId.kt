package xyz.xjnt.schoolarium_api.util

import org.hibernate.annotations.IdGeneratorType

@IdGeneratorType(CustomIdGenerator::class)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
annotation class StudentId