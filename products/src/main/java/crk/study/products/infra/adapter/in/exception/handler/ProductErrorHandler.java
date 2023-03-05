package crk.study.products.infra.adapter.in.exception.handler;

import crk.study.products.infra.adapter.in.exception.ErrorMessage;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductErrorHandler {

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException illegalStateException, WebRequest request){

        ErrorMessage error = ErrorMessage.builder()
                .message(illegalStateException.getMessage())
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request){

        ErrorMessage error = ErrorMessage.builder()
                .message(ex.getMessage())
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<Object> handleCommandHandlerInterceptor(CommandExecutionException commandExecutionException, WebRequest request){

        ErrorMessage error = ErrorMessage.builder()
                .message(commandExecutionException.getMessage())
                .date(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }

}
