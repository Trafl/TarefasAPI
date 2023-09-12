package com.Pivo.Ivo.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Pivo.Ivo.domain.exception.ToDoException;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
 
////////////////////////////////////////////////////////////////////////////	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		
		ProblemType problemType = ProblemType.UNREADABLE_MESSAGE;
		String detail = "O corpo da requisição está invalido. Verifique erro de sintaxe";
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
		
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			
		ProblemType problemType = ProblemType.INVALID_DATA;
		String detail = "Erro ao validar os campos informados no corpo da resposta.";
		
		BindingResult bindResult = ex.getBindingResult();
			
		List<Problem.Object> problemFields = bindResult.getFieldErrors().stream()
				.map(fieldErro ->{ 
					String message = messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
					
					return Problem.Object.builder()
						.name(fieldErro.getField())
						.userMessage(message)
						.build();
		}).toList();
		
		Problem body = createProblemBuilder(status, problemType, detail).fields(problemFields).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
		}
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	@ExceptionHandler(ToDoException.class)
	public ResponseEntity<?> jwtVerificationExceptionHandler(ToDoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.TODO_NOTFOUND;
		String detail = "Tarefa não foi encontrada no sistema";
		
		Problem body = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
					.status(statusCode.value())
					.timestamp(OffsetDateTime.now())
					.title(statusCode.toString())
					.detail(ex.getMessage())
					.build();
		}
		
		else if(body instanceof String) {
			body = Problem.builder()
					.status(statusCode.value())
					.timestamp(OffsetDateTime.now())
					.title(statusCode.toString())
					.detail((String)body)
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
		
		return Problem.builder()
				.status(status.value())
				.title(problemType.getTitle())
				.detail(detail)
				.timestamp(OffsetDateTime.now());
	}
}
