package cristinamastellaro.BE_U2_S2_Test.exceptions;

import java.time.LocalDate;

public class EmployeeBusyException extends RuntimeException {
    public EmployeeBusyException(String name, LocalDate date) {
        super(name + " is already booked for the date " + date);
    }
}
