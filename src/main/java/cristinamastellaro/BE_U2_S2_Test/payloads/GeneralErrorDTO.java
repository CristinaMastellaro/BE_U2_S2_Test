package cristinamastellaro.BE_U2_S2_Test.payloads;

import java.time.LocalDateTime;

public record GeneralErrorDTO(String message, LocalDateTime timeStamp) {
}
