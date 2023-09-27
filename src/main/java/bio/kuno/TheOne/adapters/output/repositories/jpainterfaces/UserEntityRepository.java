package bio.kuno.TheOne.adapters.output.repositories.jpainterfaces;

import bio.kuno.TheOne.adapters.output.repositories.dtos.UserEntityDatabaseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntityDatabaseDto, Integer> {

    Optional<UserEntityDatabaseDto> findByEmail(String email);
    Boolean existsByEmail(String email);
}
