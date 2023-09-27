package bio.kuno.TheOne.ports.output.repositories;

import bio.kuno.TheOne.domain.UserEntityDto;

import java.util.List;

public interface UserEntityPort {
    UserEntityDto findById(int id);
    UserEntityDto save(UserEntityDto user);
    List<UserEntityDto> getAll();
}
