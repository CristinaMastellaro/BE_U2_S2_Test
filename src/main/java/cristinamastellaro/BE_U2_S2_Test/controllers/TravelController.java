package cristinamastellaro.BE_U2_S2_Test.controllers;

import cristinamastellaro.BE_U2_S2_Test.entities.Travel;
import cristinamastellaro.BE_U2_S2_Test.exceptions.PayloadValidationException;
import cristinamastellaro.BE_U2_S2_Test.payloads.TravelPayload;
import cristinamastellaro.BE_U2_S2_Test.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
