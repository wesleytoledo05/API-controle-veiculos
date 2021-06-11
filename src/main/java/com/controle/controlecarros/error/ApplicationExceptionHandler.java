package com.controle.controlecarros.error;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice //tratar exceções 
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {//

  //retornar um erro quando uma rota que nao existe
  @Override
  public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
  HttpStatus status, WebRequest request) {

    String errorMessage = ex.getMessage().toString();

    ErrorMessage errorDetails = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), errorMessage);
    return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.NOT_FOUND, request);
  }

  // Verifica se os campos estao validos(nulo ou vazio)
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage().toString();

    ErrorMessage errorDetails = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errorMessage);
    return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
  }


  // Retorna um erro quando o campo do JSON esta mal formatado
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
        String errorMessage = ex.getLocalizedMessage();

        ErrorMessage errorDetails = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errorMessage);
        return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
    
  }
}