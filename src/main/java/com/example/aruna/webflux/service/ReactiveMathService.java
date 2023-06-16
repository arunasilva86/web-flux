package com.example.aruna.webflux.service;

import com.example.aruna.webflux.dto.MultiplyRequest;
import com.example.aruna.webflux.dto.Response;
import com.sun.source.tree.ReturnTree;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Flushable;
import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> calculateSquare (int value) {

        return Mono.fromSupplier(() -> value * value)
                .doOnNext(integer -> System.out.println("Generating the value " + integer))
                .map(Response::new);
    }

    public Flux<Response> generateMultiTable (int value ) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1)) // Non-Blocking delay as opposed to 'Thread.sleep (1000)'
                .map(integer -> integer * value)
                .doOnNext(integer -> System.out.println("Generated the value " + integer))
                .map(Response::new);
    }

    public Mono<Response> multiply (Mono<MultiplyRequest> multiplyRequest) {

        return multiplyRequest.map(request -> request.getFirstNumber() * request.getSecondNumber())
                .map(Response::new);
            }
}
