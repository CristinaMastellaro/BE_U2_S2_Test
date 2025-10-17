package cristinamastellaro.BE_U2_S2_Test.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsValidationPayload(
        String message, List<String> errorsList, LocalDateTime timeStamp
) {
}
