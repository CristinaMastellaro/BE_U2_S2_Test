package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Credenziali errate");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
