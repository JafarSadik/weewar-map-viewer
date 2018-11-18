package com.github.weewar.mapviewer.controllers

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.test.web.servlet.ResultActions

fun <T : Any> ResultActions.toObject(clazz: Class<T>): T = toObject(object : TypeReference<T>() {})

fun <T : Any> ResultActions.toObject(valueTypeRef: TypeReference<T>): T {
    val mapper = ObjectMapper().registerModule(KotlinModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val response = andReturn().response.contentAsString
    return mapper.readValue(response, valueTypeRef)
}


