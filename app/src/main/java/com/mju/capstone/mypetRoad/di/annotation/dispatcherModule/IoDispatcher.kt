package com.mju.capstone.mypetRoad.di.annotation.dispatcherModule

import javax.inject.Qualifier

@Target(
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@Qualifier
annotation class IoDispatcher