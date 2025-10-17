package cristinamastellaro.BE_U2_S2_Test.exceptions;

import cristinamastellaro.BE_U2_S2_Test.payloads.ErrorsValidationPayload;
import cristinamastellaro.BE_U2_S2_Test.payloads.GeneralErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(PayloadValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsValidationPayload handlePayloadValidationExc(PayloadValidationException e) {
        return new ErrorsValidationPayload(e.getMessage(), e.getErrorsList(), LocalDateTime.now());
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleEmailAlreadyUSedExc(EmailAlreadyUsedException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UsernameAlreadyUsed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleUsernameAlreadyUSedExc(UsernameAlreadyUsed e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralErrorDTO handleIdNotFoundExc(IdNotFoundException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public GeneralErrorDTO handleGeneralExc(Exception e) {
        e.getStackTrace();
        return new GeneralErrorDTO("There were issues on the server side. We will fix them as soon as possible", LocalDateTime.now());
    }
}
