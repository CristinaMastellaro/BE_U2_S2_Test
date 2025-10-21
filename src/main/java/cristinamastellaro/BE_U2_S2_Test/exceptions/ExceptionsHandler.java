package cristinamastellaro.BE_U2_S2_Test.exceptions;

import cristinamastellaro.BE_U2_S2_Test.payloads.ErrorsValidationPayload;
import cristinamastellaro.BE_U2_S2_Test.payloads.GeneralErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    // Mettere errore per numero di caratteri dell'UUID?

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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralErrorDTO handleIdNotFoundExc(NotFoundException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(EmployeeBusyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleEmployeeAlreadyBookedExc(EmployeeBusyException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(WrongStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleWrongStateExc(WrongStateException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(EmptyFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleEmptyFileExc(EmptyFileException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(WhileUploadingPictureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleWhileUploadingPictureExc(WhileUploadingPictureException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ChangeStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleChangeStateExc(ChangeStateException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GeneralErrorDTO handleUnauthorizedExc(UnauthorizedException e) {
        return new GeneralErrorDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public GeneralErrorDTO handleForbidden(AuthorizationDeniedException ex) {
        return new GeneralErrorDTO("Non hai i permessi per accedere!", LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralErrorDTO handleGeneralExc(Exception e) {
        e.getStackTrace();
        return new GeneralErrorDTO("There were issues on the server side. We will fix them as soon as possible", LocalDateTime.now());
    }
}
