package com.example.aruna.webflux.config;

import com.example.aruna.webflux.dto.InvalidParamErrorResponse;
import com.example.aruna.webflux.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {
    
    @Autowired
    private MathRequestHandler mathRequestHandler;

    @Bean
    RouterFunction<ServerResponse> serverResponseRouterFunction () {
        return RouterFunctions.route()
                .GET("/router/square/{input}", request -> mathRequestHandler.squareHandler(request))
                .GET("/router/multi-table/stream/{input}", request -> mathRequestHandler.multiTableHAndler(request))
                .onError(InvalidInputException.class, (exception, request) -> handleError(exception, request))
                .build();

    }

    private Mono<ServerResponse> handleError (InvalidInputException  exception, ServerRequest request) {

        InvalidParamErrorResponse response = new InvalidParamErrorResponse();
        response.setErrorCode(InvalidInputException.errorCode);
        response.setMessage(exception.getMessage());
        response.setBody(exception.getInput());
        return ServerResponse.badRequest().bodyValue(response);

    }
    
}
