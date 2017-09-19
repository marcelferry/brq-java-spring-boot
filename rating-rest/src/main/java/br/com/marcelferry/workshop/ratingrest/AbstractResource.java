package br.com.marcelferry.workshop.ratingrest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AbstractResource {

	@CrossOrigin(allowedHeaders = "foo", origins = "*")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ApiResponse exception(NotFoundException e) {
		return new ApiResponse(ApiResponse.ERROR, e.getMessage());
	}
}