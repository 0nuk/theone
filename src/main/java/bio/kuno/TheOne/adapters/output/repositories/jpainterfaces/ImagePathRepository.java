package bio.kuno.TheOne.adapters.output.repositories.jpainterfaces;

import bio.kuno.TheOne.adapters.output.repositories.dtos.ImageDatabaseDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagePathRepository extends JpaRepository<ImageDatabaseDto, Integer> {
}
