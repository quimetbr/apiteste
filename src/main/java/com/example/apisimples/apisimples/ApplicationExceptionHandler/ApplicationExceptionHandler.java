package com.example.apisimples.apisimples.ApplicationExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.apisimples.apisimples.DTO.APIError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	//Método para adicionar com a anotação algum exception especifico, no caso nullpointer. 
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(Exception e) {
		log.info("Dentro do Exception especifico para o null");
        APIError error = new APIError(HttpStatus.BAD_GATEWAY.value(), "Error null pointer"); 
		e.printStackTrace();
		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
	}
	
	//Agora tratando todos os exceptions de vez  
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		log.info("Dentro do Exception geral");
        APIError error = new APIError(HttpStatus.BAD_GATEWAY.value(), "Error de processamento geral" ); 
		e.printStackTrace();
		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
	}
}
