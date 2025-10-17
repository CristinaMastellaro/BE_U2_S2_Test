package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class ChangeStateException extends RuntimeException {
    public ChangeStateException() {
        super("Check that the body of your request contains the correct elements and that the keys are written correctly");
    }
}
