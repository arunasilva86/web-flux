package com.example.aruna.webflux.config;

import com.example.aruna.webflux.config.MathRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    
    @Autowired
    private MathRequestHandler requestHandler;

    @Bean
    RouterFunction<ServerResponse> serverResponseRouterFunction () {
        return RouterFunctions.route()
                .GET("/router/square/{input}", request -> requestHandler.squareHandler(request))
                .build();

    }
    
}
