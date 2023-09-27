package bio.kuno.TheOne.ports.output.imagestorage;

import bio.kuno.TheOne.domain.ImageDto;
import bio.kuno.TheOne.domain.UserEntityDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageStoragePort {
    List<String> getUrlsForImages(UserEntityDto userEntityDto);
    List<ImageDto> uploadImages(List<MultipartFile> images);
    byte[] getImage(String fileName);
    void init();
    public void deleteAll();
}
