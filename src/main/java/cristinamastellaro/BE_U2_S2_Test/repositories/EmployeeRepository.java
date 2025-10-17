package cristinamastellaro.BE_U2_S2_Test.repositories;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
