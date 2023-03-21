package br.com.matheusbespalec.algaworks.esr.api.exception.handler;

import br.com.matheusbespalec.algaworks.esr.api.exception.model.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<ErrorValidationFields.Field> fields = new ArrayList<ErrorValidationFields.Field>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> fields.add(new ErrorValidationFields.Field(
                ((FieldError) objectError).getField(),
                objectError.getDefaultMessage()
        )));

        ErrorValidationFields errorValidationFields = new ErrorValidationFields();
        errorValidationFields.setMessage(status.name());
        errorValidationFields.setStatus(status.value());
        errorValidationFields.setFields(fields);

        return handleExceptionInternal(ex, errorValidationFields, headers, status, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {

        ErrorValidationFields errorValidationFields = new ErrorValidationFields();
        errorValidationFields.setMessage(badRequestException.getMessage());
        errorValidationFields.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorValidationFields, HttpStatus.BAD_REQUEST);
    }
}
