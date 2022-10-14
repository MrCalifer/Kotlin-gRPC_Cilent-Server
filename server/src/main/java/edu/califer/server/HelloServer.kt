package edu.califer.server

import edu.califer.protos.GreeterGrpcKt
import edu.califer.protos.Request
import edu.califer.protos.Response
import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

class HelloServer(private val port: Int) {
    val server: Server = ServerBuilder
        .forPort(port)
        .addService(HelloService())
        .build()

    fun start() {
        server.start()
        println("*** Server started, listening on $port ***")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                this@HelloServer.stop()
                println("*** Server shut down ***")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }


    private class HelloService : GreeterGrpcKt.GreeterCoroutineImplBase(
        coroutineContext = Executors.newFixedThreadPool(1).asCoroutineDispatcher()
    ) {
        override suspend fun sayHello(request: Request): Response {
            return Response.newBuilder()
                .setMessage("Response to "+request.name)
                .build()
        }
    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    val server = HelloServer(port)
    server.start()
    server.blockUntilShutdown()
}