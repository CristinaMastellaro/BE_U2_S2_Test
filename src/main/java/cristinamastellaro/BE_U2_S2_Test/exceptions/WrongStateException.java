package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class WrongStateException extends RuntimeException {
    public WrongStateException() {
        super("Check that the state is written correctly. You can choose between COMPLETED and PROGRAMMED");
    }
}
