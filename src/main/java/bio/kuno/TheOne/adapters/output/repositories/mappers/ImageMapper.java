package bio.kuno.TheOne.adapters.output.repositories.mappers;

import bio.kuno.TheOne.adapters.output.repositories.dtos.ImageDatabaseDto;
import bio.kuno.TheOne.domain.ImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper( ImageMapper.class );
    ImageDto toImagePathDto(ImageDatabaseDto imagePathDatabaseDto);
    ImageDatabaseDto toImagePathDatabaseDto(ImageDto imagePathDto);
}
