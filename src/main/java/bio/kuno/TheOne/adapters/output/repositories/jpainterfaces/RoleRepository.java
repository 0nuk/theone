package bio.kuno.TheOne.adapters.output.repositories.jpainterfaces;

import bio.kuno.TheOne.adapters.output.repositories.dtos.RoleDatabaseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDatabaseDto, Integer> {
    RoleDatabaseDto findByName(String name);
}
