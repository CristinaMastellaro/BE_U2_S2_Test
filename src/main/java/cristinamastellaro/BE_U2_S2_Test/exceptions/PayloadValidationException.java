package cristinamastellaro.BE_U2_S2_Test.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class PayloadValidationException extends RuntimeException {
    private List<String> errorsList;

    public PayloadValidationException(List<String> errorsList) {
        super("The format of the payload is not correct");
        this.errorsList = errorsList;
    }
}
