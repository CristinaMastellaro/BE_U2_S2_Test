package cristinamastellaro.BE_U2_S2_Test.controllers;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.exceptions.PayloadValidationException;
import cristinamastellaro.BE_U2_S2_Test.payloads.EmployeePayload;
import cristinamastellaro.BE_U2_S2_Test.payloads.LoginDTO;
import cristinamastellaro.BE_U2_S2_Test.payloads.LoginResponseDTO;
import cristinamastellaro.BE_U2_S2_Test.services.EmployeeService;
import cristinamastellaro.BE_U2_S2_Test.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LoginController {

    @Autowired
    private LoginService logService;
    @Autowired
    private EmployeeService eServ;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO login) {
        return new LoginResponseDTO(logService.checkInfoAndGenerateToken(login));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody @Validated EmployeePayload employee, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return eServ.saveEmployee(employee);
    }
}
