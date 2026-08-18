package com.passeios_app.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErroResponse tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
		return new ErroResponse(404, ex.getMessage());
	}
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErroResponse tratarRegraNegocioException(RegraNegocioException ex) {
		return new ErroResponse(409, ex.getMessage());
	}

}
