package cristinamastellaro.BE_U2_S2_Test.controllers;

import cristinamastellaro.BE_U2_S2_Test.entities.Travel;
import cristinamastellaro.BE_U2_S2_Test.exceptions.ChangeStateException;
import cristinamastellaro.BE_U2_S2_Test.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_Test.exceptions.PayloadValidationException;
import cristinamastellaro.BE_U2_S2_Test.exceptions.WrongStateException;
import cristinamastellaro.BE_U2_S2_Test.payloads.TravelPayload;
import cristinamastellaro.BE_U2_S2_Test.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/travels")
public class TravelController {
    @Autowired
    private TravelService tServ;

    @GetMapping
    public List<Travel> findAllTravels() {
        return tServ.findAllTravels();
    }

    @PostMapping
    public Travel saveTravel(@RequestBody @Validated TravelPayload tP, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return tServ.saveTravel(tP);
    }

    @GetMapping("/{travelId}")
    public Travel findTravelById(@PathVariable UUID travelId) {
        return tServ.findTravelById(travelId);
    }

    @PutMapping("/{travelId}")
    public Travel updateTravelById(@PathVariable UUID travelId, @RequestBody @Validated TravelPayload tP, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return tServ.updateTravel(travelId, tP);
    }

    @DeleteMapping("/{travelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTravelById(@PathVariable UUID travelId) {
        tServ.deleteTravel(travelId);
    }

    @PatchMapping("/{travelId}/employee")
    public Travel changeEmployee(@PathVariable UUID travelId, @RequestBody Map<String, UUID> employeeId) {
        return tServ.addEmployeeToTravel(travelId, employeeId);
    }

    @PatchMapping("/{travelId}/state")
    public Travel changeState(@PathVariable UUID travelId, @RequestBody Map<String, String> newState) {
        try {
            return tServ.changeState(travelId, newState);
        } catch (Exception e) {
            if (e.getClass() == WrongStateException.class) {
                throw new WrongStateException();
            } else if (e.getClass() == IdNotFoundException.class) {
                throw new IdNotFoundException(travelId);
            } else {
                throw new ChangeStateException();
            }
        }
    }
}
