package com.macoder.authentication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.macoder.core.entity")
class AuthenticationExampleApplication

fun main(args: Array<String>) {
    runApplication<AuthenticationExampleApplication>(*args)
}
