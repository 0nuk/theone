package bio.kuno.TheOne.adapters.output.repositories.jpainterfaces;

import bio.kuno.TheOne.adapters.output.repositories.dtos.PrivilegeDatabaseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<PrivilegeDatabaseDto, Long> {
    PrivilegeDatabaseDto findByName(String name);
}