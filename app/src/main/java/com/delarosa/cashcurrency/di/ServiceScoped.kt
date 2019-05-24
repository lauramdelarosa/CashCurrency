package com.delarosa.cashcurrency.di

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import javax.inject.Scope

/**
 * The ServiceScoped custom scoping annotation specifies that the lifespan of a dependency be
 * the same as that of a Service. This is used to annotate dependencies that behave like a
 * singleton within the lifespan of a Service.
 */
// TODO: Migrate all Scope definitions to kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(ElementType.TYPE, ElementType.METHOD)
annotation class ServiceScoped
