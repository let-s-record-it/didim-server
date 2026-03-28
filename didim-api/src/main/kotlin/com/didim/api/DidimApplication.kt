package com.didim.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.didim"])
class DidimApplication

fun main(args: Array<String>) {
    runApplication<DidimApplication>(*args)
}