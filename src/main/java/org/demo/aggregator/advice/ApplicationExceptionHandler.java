package org.demo.aggregator.advice;

import org.demo.aggregator.exceptions.CustomerNotFoundException;
import org.demo.aggregator.exceptions.InvalidTradeRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.function.Consumer;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleException(CustomerNotFoundException ex) {
        return buildProblemDetail(HttpStatus.NOT_FOUND, ex, problem -> {
            problem.setTitle("Customer not found");
            problem.setType(URI.create("http://example.com/problems/customer-not-found"));
        });
    }

    @ExceptionHandler(InvalidTradeRequestException.class)
    public ProblemDetail handleException(InvalidTradeRequestException ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex, problem -> {
            problem.setTitle("Invalid Trade Request");
            problem.setType(URI.create("http://example.com/problems/invalid-trade-request"));
        });
    }


    private ProblemDetail buildProblemDetail(HttpStatus status, Exception ex, Consumer<ProblemDetail> consumer) {
        var problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        consumer.accept(problem);
        return problem;
    }
}
