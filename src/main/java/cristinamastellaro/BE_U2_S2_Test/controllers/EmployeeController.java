package cristinamastellaro.BE_U2_S2_Test.controllers;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.exceptions.PayloadValidationException;
import cristinamastellaro.BE_U2_S2_Test.payloads.EmployeePayload;
import cristinamastellaro.BE_U2_S2_Test.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService eServ;

    @GetMapping("/me")
    public Employee findEmployeeById(@AuthenticationPrincipal Employee currentAuthenticatedUser) {
        return eServ.findEmployeeById(currentAuthenticatedUser.getId());
    }

    @PutMapping("/me")
    public Employee updateEmployee(@AuthenticationPrincipal Employee currentAuthenticatedUser, @RequestBody @Validated EmployeePayload eP, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return eServ.updateEmployee(currentAuthenticatedUser.getId(), eP);
    }

    @PatchMapping("/me/picture")
    public Employee updatePicture(@AuthenticationPrincipal Employee currentAuthenticatedUser, @RequestParam("picture") MultipartFile picture) {
        return eServ.updatePictureEmployee(currentAuthenticatedUser.getId(), picture);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@AuthenticationPrincipal Employee currentAuthenticatedUser) {
        eServ.deleteEmployee(currentAuthenticatedUser.getId());
    }

    // Metti la paginazione, se hai tempo
    @GetMapping
    public List<Employee> getAllEmployees() {
        return eServ.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployeeById(@PathVariable UUID employeeId) {
        return eServ.findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody @Validated EmployeePayload eP, BindingResult validation) {
        if (validation.hasErrors())
            throw new PayloadValidationException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        return eServ.updateEmployee(employeeId, eP);
    }

    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable UUID employeeId) {
        eServ.deleteEmployee(employeeId);
    }
}
