package repository;

import entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EmployeeRepository<T extends Employee, Id extends Serializable>
        extends JpaRepository<T,Id> {
}
