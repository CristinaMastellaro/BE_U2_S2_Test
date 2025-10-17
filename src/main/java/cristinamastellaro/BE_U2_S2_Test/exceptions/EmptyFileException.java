package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException() {
        super("The file is empty");
    }
}
