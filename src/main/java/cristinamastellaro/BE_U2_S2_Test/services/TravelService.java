package cristinamastellaro.BE_U2_S2_Test.services;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.entities.Travel;
import cristinamastellaro.BE_U2_S2_Test.exceptions.EmployeeBusyException;
import cristinamastellaro.BE_U2_S2_Test.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_Test.payloads.TravelPayload;
import cristinamastellaro.BE_U2_S2_Test.repositories.TravelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

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
                    throw new EmployeeBusyException(travel.getEmployee().getName(), travel.getEmployee().getId(), travel.getDate());
            }
        }

        Travel travel = new Travel(newTravel.destination(), date, emp);

        tRepo.save(travel);
        log.info("The travel with destination " + newTravel.destination() + " has been saved for the date " + newTravel.date());
        return travel;
    }

    public Travel findTravelById(UUID id) {
        return tRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    public Travel updateTravel(UUID id, TravelPayload tP) {
        Travel travelToUpdate = findTravelById(id);

        //Assicuriamoci che l'id dell'employee esista
        Employee emp = eServ.findEmployeeById(tP.employeeId());

        // Anche in questo caso assicuriamoci che la nuova data, se è stata cambiata, sia libera per il dipendente
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(tP.date(), formatter);
        if (travelToUpdate.getDate() != date) {
            List<Travel> travelsOnDate = tRepo.findByDate(date);
            if (!travelsOnDate.isEmpty()) {
                for (Travel travel : travelsOnDate) {
                    if (travel.getEmployee().getId() == tP.employeeId())
                        throw new EmployeeBusyException(travel.getEmployee().getName(), travel.getEmployee().getId(), travel.getDate());
                }
            }
        }

        travelToUpdate.setDestination(tP.destination());
        travelToUpdate.setDate(date);
        travelToUpdate.setEmployee(emp);

        tRepo.save(travelToUpdate);

        log.info("Travel updated successfully");
        return travelToUpdate;

    }
}
