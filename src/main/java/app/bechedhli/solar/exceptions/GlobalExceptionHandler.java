package app.bechedhli.solar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PieceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePieceNotFound(PieceNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InterventionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInterventionNotFound(InterventionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StockInsuffisantException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleStockInsuffisant(StockInsuffisantException ex) {
        return ex.getMessage();
    }
}