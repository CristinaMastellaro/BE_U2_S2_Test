package cristinamastellaro.BE_U2_S2_Test.services;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.entities.Travel;
import cristinamastellaro.BE_U2_S2_Test.exceptions.EmployeeBusyException;
import cristinamastellaro.BE_U2_S2_Test.payloads.TravelPayload;
import cristinamastellaro.BE_U2_S2_Test.repositories.TravelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class TravelService {
    @Autowired
    private TravelRepository tRepo;
    @Autowired
    private EmployeeService eServ;

    // Metti la paginazione, se hai tempo
    public List<Travel> findAllTravels() {
        return tRepo.findAll();
    }

    public Travel saveTravel(TravelPayload newTravel) {
        // Accertiamoci che il dipendente esista
        Employee emp = eServ.findEmployeeById(newTravel.employeeId());

        // Accertiamoci che il dipendente non sia impegnato nella data del viaggio
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(newTravel.date(), formatter);
        List<Travel> travelsOnDate = tRepo.findByDate(date);
        if (!travelsOnDate.isEmpty()) {
            for (Travel travel : travelsOnDate) {
                if (travel.getEmployee().getId() == newTravel.employeeId())
                    throw new EmployeeBusyException(travel.getEmployee().getName(), travel.getDate());
            }
        }

        Travel travel = new Travel(newTravel.destination(), date, emp);

        tRepo.save(travel);
        log.info("The travel with destination " + newTravel.destination() + " has been saved for the date " + newTravel.date());
        return travel;
    }
}
