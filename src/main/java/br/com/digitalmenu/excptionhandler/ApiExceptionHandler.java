package br.com.digitalmenu.excptionhandler;

import br.com.digitalmenu.exception.EntityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Exception.Field> fields = new ArrayList<Exception.Field>();

        for(ObjectError error : ex.getBindingResult().getAllErrors()) {

            String name = ((FieldError) error).getField();
            String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new Exception.Field(name, msg));

        }

        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setTitle("One or more fields are invalid. Fill it out and try again.");
        exception.setTime(OffsetDateTime.now());
        exception.setFields(fields);

        return super.handleExceptionInternal(ex, exception, headers, status, request);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<Object> handlerBusiness(EntityAlreadyExistsException ex, WebRequest request) {

        HttpStatus status = BAD_REQUEST;
        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setTitle(ex.getMessage());
        exception.setTime(OffsetDateTime.now());

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);
    }
}
