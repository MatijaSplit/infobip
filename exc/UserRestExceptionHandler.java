package com.matija.infobip.exc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {
	//Add an exception handler using @ExceptionHandler
		@ExceptionHandler
		public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc){
			
			// UserErrorResponse
			UserErrorResponse error=new UserErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
		
		
		@ExceptionHandler
		public ResponseEntity<LinkErrorResponse> handleException(LinkNotFoundException exc){
			
			LinkErrorResponse error=new LinkErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
		@ExceptionHandler
		public ResponseEntity<LinkErrorResponse> handleException(InvalidURLException exc){
			
			LinkErrorResponse error=new LinkErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
		@ExceptionHandler
		public ResponseEntity<LinkErrorResponse> handleException(LinkshortenerException exc){
			
			LinkErrorResponse error=new LinkErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
		
		//Ostali exceptioni
		@ExceptionHandler
		public ResponseEntity<UserErrorResponse> handleExcpetion(Exception exc){
			
			UserErrorResponse error=new UserErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
			
		}

}
