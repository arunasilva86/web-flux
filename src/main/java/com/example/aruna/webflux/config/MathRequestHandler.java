package com.example.aruna.webflux.config;

import com.example.aruna.webflux.dto.Response;
import com.example.aruna.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class MathRequestHandler {

    @Autowired
    private ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler (ServerRequest request) {
        int value = Integer.parseInt(request.pathVariable("input"));
        Mono<Response> responseMono = reactiveMathService.calculateSquare(value);
        return ServerResponse.ok().body(responseMono, Response.class); // .body() because responseMono is a publisher which we sent as part of the response. if it was not a publisher but an object/dto then we must use .bodyValue() instead of .body()

    }

}
