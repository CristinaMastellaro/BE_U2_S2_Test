package cristinamastellaro.BE_U2_S2_Test.services;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.exceptions.EmailAlreadyUsedException;
import cristinamastellaro.BE_U2_S2_Test.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_Test.exceptions.UsernameAlreadyUsed;
import cristinamastellaro.BE_U2_S2_Test.payloads.EmployeePayload;
import cristinamastellaro.BE_U2_S2_Test.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository eRepo;

    public List<Employee> findAll() {
        return eRepo.findAll();
    }

    public Employee saveEmployee(EmployeePayload employeePayload) {
        // Verifichiamo che l'email e l'username non siano già in uso
        if (eRepo.existsByEmail(employeePayload.email())) throw new EmailAlreadyUsedException(employeePayload.email());
        if (eRepo.existsByUsername(employeePayload.username()))
            throw new UsernameAlreadyUsed(employeePayload.username());
        Employee employee = new Employee(employeePayload.username(), employeePayload.name(), employeePayload.surname(), employeePayload.email());

        eRepo.save(employee);

        log.info("The employee has been registered correctly!");

        return employee;
    }

    public Employee findEmployeeById(UUID id) {
        return eRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    public Employee updateEmployee(UUID id, EmployeePayload newInfo) {
        Employee emp = findEmployeeById(id);
        // Accertiamoci anche qui che email e username non siano già in uso
        if (!Objects.equals(emp.getEmail(), newInfo.email()) && eRepo.existsByEmail(newInfo.email()))
            throw new EmailAlreadyUsedException(newInfo.email());
        if (!Objects.equals(emp.getUsername(), newInfo.username()) && eRepo.existsByUsername(newInfo.username()))
            throw new UsernameAlreadyUsed(newInfo.username());

        emp.setEmail(newInfo.email());
        emp.setName(newInfo.name());
        emp.setSurname(newInfo.surname());
        emp.setUsername(newInfo.username());

        eRepo.save(emp);
        log.info("The employee's info has been correctly updated!");
        return emp;
    }
}
