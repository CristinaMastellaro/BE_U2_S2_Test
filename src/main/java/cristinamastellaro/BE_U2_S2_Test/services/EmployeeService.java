package cristinamastellaro.BE_U2_S2_Test.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.exceptions.*;
import cristinamastellaro.BE_U2_S2_Test.payloads.EmployeePayload;
import cristinamastellaro.BE_U2_S2_Test.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository eRepo;
    @Autowired
    private Cloudinary imageUploader;
    @Autowired
    private PasswordEncoder bCrypt;

    public List<Employee> findAll() {
        return eRepo.findAll();
    }

    public Employee saveEmployee(EmployeePayload employeePayload) {
        // Verifichiamo che l'email e l'username non siano già in uso
        if (eRepo.existsByEmail(employeePayload.email())) throw new EmailAlreadyUsedException(employeePayload.email());
        if (eRepo.existsByUsername(employeePayload.username()))
            throw new UsernameAlreadyUsed(employeePayload.username());
        Employee employee = new Employee(employeePayload.username(), employeePayload.name(), employeePayload.surname(), employeePayload.email(), bCrypt.encode(employeePayload.password()));

        eRepo.save(employee);

        log.info("The employee has been registered correctly!");

        return employee;
    }

    public Employee findEmployeeById(UUID id) {
        return eRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
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

    public void deleteEmployee(UUID id) {
        Employee found = findEmployeeById(id);
        eRepo.delete(found);
        log.info("Employee's info has been deleted");
    }

    public Employee updatePictureEmployee(UUID id, MultipartFile picture) {
        Employee emp = findEmployeeById(id);
        if (picture.isEmpty()) throw new EmptyFileException();

        try {
            Map result = imageUploader.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("url");
            emp.setPicture(imageUrl);
            eRepo.save(emp);
            log.info("The picture has been uploaded");
            return emp;
        } catch (IOException e) {
            throw new WhileUploadingPictureException(e.getMessage());
        }

    }

    public Employee findEmployeeByEmail(String email) {
        return eRepo.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }
}
