package springj.authenticas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import springj.authenticas.payload.ApiResponse;


@ControllerAdvice
class AppAdvice {

    @ResponseBody
    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiResponse appHandler(AppException ex) {
        return new ApiResponse(false, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse badRequestHandler(BadRequestException ex) {
        return new ApiResponse(false, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiResponse resourceNotFoundHandler(ResourceNotFoundException ex) {
        return new ApiResponse(false, ex.getMessage());
    }

}