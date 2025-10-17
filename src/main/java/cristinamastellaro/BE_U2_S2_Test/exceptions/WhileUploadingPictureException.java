package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class WhileUploadingPictureException extends RuntimeException {
    public WhileUploadingPictureException(String message) {
        super("There was an error while uploading the picture:\n" + message);
    }
}
