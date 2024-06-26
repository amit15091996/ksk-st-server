package com.khadbhandarserver.inventory.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.khadbhandarserver.inventory.helper.AppConstant;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<Object, Object>> noDataAvailable(NotFoundException notFoundException,
			WebRequest request) {

		CustomException customException = new CustomException(AppConstant.Not_Found, AppConstant.Not_Found_desc,
				LocalDateTime.now(), notFoundException.getMessage(), request.getDescription(false));
		Map<Object, Object> notFound = new HashMap<>();
		notFound.put(AppConstant.statusCode, customException.getStatusCode());
		notFound.put(AppConstant.status, customException.getStatus());
		notFound.put(AppConstant.timeStamp, customException.getTimestamp().toString());
		notFound.put(AppConstant.statusMessage, customException.getMessage());
		notFound.put(AppConstant.description, customException.getDescription());
		return ResponseEntity.ok(notFound);

	}

	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<Map<Object, Object>> badRequest(BadRequest badRequest, WebRequest request) {

		CustomException badRequestException = new CustomException(AppConstant.Bad_Request, AppConstant.Bad_Request_desc,
				LocalDateTime.now(), badRequest.getMessage(), request.getDescription(false));

		Map<Object, Object> badRequestMap = new HashMap<>();

		badRequestMap.put(AppConstant.statusCode, badRequestException.getStatusCode());
		badRequestMap.put(AppConstant.status, badRequestException.getStatus());
		badRequestMap.put(AppConstant.timeStamp, badRequestException.getTimestamp().toString());
		badRequestMap.put(AppConstant.statusMessage, badRequestException.getMessage());
		badRequestMap.put(AppConstant.description, badRequestException.getDescription());
		return ResponseEntity.ok(badRequestMap);

	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<Object, Object>> MethodArgumentNotValid(MethodArgumentNotValidException methodArgument,
			WebRequest request) {
		

		CustomException methodArgumentException = new CustomException(AppConstant.Bad_Request,
				AppConstant.Bad_Request_desc, LocalDateTime.now(), methodArgument.getAllErrors().stream()
						.map(t -> t.getDefaultMessage()).collect(Collectors.toList()).toString(),
				request.getDescription(false));

		Map<Object, Object> methodArgumentMap = new HashMap<>();

		methodArgumentMap.put(AppConstant.statusCode, methodArgumentException.getStatusCode());
		methodArgumentMap.put(AppConstant.status, methodArgumentException.getStatus());
		methodArgumentMap.put(AppConstant.timeStamp, methodArgumentException.getTimestamp().toString());
		methodArgumentMap.put(AppConstant.statusMessage, methodArgumentException.getMessage());
		methodArgumentMap.put(AppConstant.description, methodArgumentException.getDescription());
		return ResponseEntity.ok(methodArgumentMap);

	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Map<Object, Object>> MessageNotReadableExceptionHandler(HttpMessageNotReadableException ex,
			WebRequest request) {

		CustomException messageNotReadable = new CustomException(AppConstant.Bad_Request, AppConstant.Bad_Request_desc,
				LocalDateTime.now(), AppConstant.requestBodyMissing, request.getDescription(false));

		Map<Object, Object> NotReadable = new HashMap<>();

		NotReadable.put(AppConstant.statusCode, messageNotReadable.getStatusCode());
		NotReadable.put(AppConstant.status, messageNotReadable.getStatus());
		NotReadable.put(AppConstant.timeStamp, messageNotReadable.getTimestamp().toString());
		NotReadable.put(AppConstant.statusMessage, messageNotReadable.getMessage());
		NotReadable.put(AppConstant.description, messageNotReadable.getDescription());
		return ResponseEntity.ok(NotReadable);

	}

	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Map<Object, Object>> internalServerError(InternalServerError internalServerError,
			WebRequest request) {

		CustomException badRequestException = new CustomException(AppConstant.Internal_Server_Error,
				AppConstant.Internal_Server_Error_desc, LocalDateTime.now(), internalServerError.getMessage(),
				request.getDescription(false));

		Map<Object, Object> internalServerErrorMap = new HashMap<>();

		internalServerErrorMap.put(AppConstant.statusCode, badRequestException.getStatusCode());
		internalServerErrorMap.put(AppConstant.status, badRequestException.getStatus());
		internalServerErrorMap.put(AppConstant.timeStamp, badRequestException.getTimestamp().toString());
		internalServerErrorMap.put(AppConstant.statusMessage, badRequestException.getMessage());
		internalServerErrorMap.put(AppConstant.description, badRequestException.getDescription());
		return ResponseEntity.ok(internalServerErrorMap);

	}

	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Map<Object, Object>> responseStatusError(ResponseStatusException responseStatusException,
			WebRequest request) {

		CustomException responseStatus = new CustomException(AppConstant.Bad_Request,
				AppConstant.Bad_Request_desc, LocalDateTime.now(), "Please check your input field as all field's are mandatory  ",
				request.getDescription(false));
		Map<Object, Object> responseStatusMap = new HashMap<>();
		responseStatusMap.put(AppConstant.statusCode, responseStatus.getStatusCode());
		responseStatusMap.put(AppConstant.status, responseStatus.getStatus());
		responseStatusMap.put(AppConstant.timeStamp, responseStatus.getTimestamp().toString());
		responseStatusMap.put(AppConstant.statusMessage, responseStatus.getMessage());
		responseStatusMap.put(AppConstant.description, responseStatus.getDescription());
		return ResponseEntity.ok(responseStatusMap);

	}
	
	 
	
}
