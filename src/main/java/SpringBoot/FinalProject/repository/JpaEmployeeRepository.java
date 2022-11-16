package SpringBoot.FinalProject.repository;

import SpringBoot.FinalProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<Employee, UUID> {

}
