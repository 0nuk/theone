package bio.kuno.TheOne.adapters.output.repositories.mappers;

import bio.kuno.TheOne.adapters.output.repositories.dtos.UserEntityDatabaseDto;
import bio.kuno.TheOne.domain.UserEntityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper( UserEntityMapper.class );
    UserEntityDatabaseDto toUser(UserEntityDto userDto);
    UserEntityDto toUserDto(UserEntityDatabaseDto user);

}
