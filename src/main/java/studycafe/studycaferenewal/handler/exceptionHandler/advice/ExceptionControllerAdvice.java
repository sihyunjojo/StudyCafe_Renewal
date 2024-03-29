package studycafe.studycaferenewal.handler.exceptionHandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import studycafe.studycaferenewal.exception.BadRequestException;
import studycafe.studycaferenewal.exception.UserException;
import studycafe.studycaferenewal.handler.exceptionHandler.ErrorResult;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) // 2개이상 가능
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(RuntimeException.class) // 2개이상 가능
    public ModelAndView badRequestHandler(BadRequestException e) {
        log.error("[badRequestHandler] ex", e);
        return new ModelAndView("error/400");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    @ExceptionHandler() //생략시 메서드 파라메터의 값이 지정된다.
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

}
