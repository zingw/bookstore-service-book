package com.duclv.bookstore.servicebook.config;

import com.duclv.bookstore.servicebook.service.BookServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ServerStarter {
    @Value(value = "${grpc.server.port}")
    private int SERVER_PORT;
    private final BookServiceImpl bookService;

    @Bean
    public void start() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(SERVER_PORT)
                .addService(bookService)
                .build()
                .start();
        log.debug("GRPC STARTED, LISTENING ON PORT : {} ", SERVER_PORT);
        Runtime.getRuntime().addShutdownHook(new Thread( () ->{
            System.err.println("Shutting down grpc server");
            if(server != null){
                server.shutdown();
            }
            System.err.println("Server shut down");
        }));
        server.awaitTermination();
    }
}
