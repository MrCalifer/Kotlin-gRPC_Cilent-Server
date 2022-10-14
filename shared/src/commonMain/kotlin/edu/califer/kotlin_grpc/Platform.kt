package edu.califer.kotlin_grpc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform