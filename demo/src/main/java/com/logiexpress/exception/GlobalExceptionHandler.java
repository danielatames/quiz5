package com.logiexpress.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PesoExcedidoException.class)
    public ProblemDetail handlePesoExcedido(PesoExcedidoException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle("Peso Excedido");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ProblemDetail handleClienteNoEncontrado(ClienteNoEncontradoException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Cliente No Encontrado");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}