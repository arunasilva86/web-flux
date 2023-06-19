package com.example.aruna.webflux.controller;

import com.example.aruna.webflux.dto.MultiplyRequestDto;
import com.example.aruna.webflux.dto.Response;
import com.example.aruna.webflux.exception.InvalidInputException;
import com.example.aruna.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{value}") // Mono example
    public Mono<Response> getSquare(@PathVariable int value) {
        Mono<Response> responseMono = reactiveMathService.calculateSquare(value);
        return responseMono;
    }

    @GetMapping("square-validate/{value}") // Mono example
    public Mono<Response> getSquareWithValidate(@PathVariable int value) {

        Mono<Response> responseMono = reactiveMathService.calculateSquare(value);
        return responseMono;
    }


    @GetMapping(value = "multi-table/{value}", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // Flux example
    public Flux<Response> getMultiTable(@PathVariable int value) {
        Flux<Response> responseFlux = reactiveMathService.generateMultiTable(value);
        return responseFlux;
    }

    // validate the input parameters and use Controller-Advice to handle error
    @PostMapping(value = "multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> multiplyRequestMono) {

        Mono<Response> responseMono = multiplyRequestMono.handle((multiplyRequestDto, synchronousSink) -> {
                    if (multiplyRequestDto.getFirstNumber() < 0 || multiplyRequestDto.getSecondNumber() < 0) {
                        // Have configured a controller advice to handle InvalidInputException
                        synchronousSink.error(new InvalidInputException(multiplyRequestDto, "firstNumber and Second number both should be > 0"));
                    } else {
                        synchronousSink.next(multiplyRequestDto);
                    }
                })
                .cast(MultiplyRequestDto.class)
                .map(reactiveMathService::multiplyValue)
                .map(Response::new);

        return responseMono;

    }
}
