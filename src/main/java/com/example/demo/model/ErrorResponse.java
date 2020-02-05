package com.example.demo.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
	
	public static <T> ResponseEntity<T> noContent(){
	       return withStatus(HttpStatus.NO_CONTENT);
	    }

	    public static <T> ResponseEntity<T> internalServerError(){
	       return withStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    public static <T> ResponseEntity<T> accepted(){
	       return withStatus(HttpStatus.ACCEPTED);
	    }

	    private static <T> ResponseEntity<T> withStatus(HttpStatus status){
	       return new ResponseEntity<T>(status);
	    }

		public static <T> ResponseEntity<T> Invalid(){
		       return withStatus(HttpStatus.NOT_FOUND);
		    }
	
	    
	    
	    
		}
		


