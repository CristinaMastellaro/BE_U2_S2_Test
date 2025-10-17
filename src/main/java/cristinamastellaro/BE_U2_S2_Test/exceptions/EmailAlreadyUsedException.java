package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("The email " + email + " has already been used");
    }
}
