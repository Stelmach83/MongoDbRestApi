package dev.stelmach.homeworkapi.exception;

import dev.stelmach.homeworkapi.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Not found - 404 exception handling.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> handle404Exception(NoHandlerFoundException e) {
        ApiResponse error = new ApiResponse();
        error.setStatus(404);
        error.setMessage("End-point not found.");
        error.setResult(e.getMessage());
        log(error);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Incorrect json structure exception handling.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleJsonException(HttpMessageNotReadableException e) {
        ApiResponse error = new ApiResponse();
        error.setStatus(400);
        error.setMessage("Incorrect json structure.");
        error.setResult(e.getMessage());
        log(error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Database resource exception handling.
     */
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ApiResponse> handleDatabaseException(ResourceException e) {
        ApiResponse error = new ApiResponse();
        error.setStatus(400);
        error.setMessage("Resource exception.");
        error.setResult(e.getMessage());
        log(error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Employee validation exception handling.
     */
    @ExceptionHandler(EmployeeModelException.class)
    public ResponseEntity<ApiResponse> handleValidationException(EmployeeModelException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(e.getMessage());
        ApiResponse error = new ApiResponse();
        error.setStatus(412);
        error.setMessage("Validation error.");
        error.setResult(errorMsg);
        log(error);
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

    private void log(ApiResponse error) {
        if (log.isErrorEnabled()) {
            String code = "Error code: %s - %s";
            log.error(String.format(code, error.getStatus(), error.getResult()));
        }
    }
}
