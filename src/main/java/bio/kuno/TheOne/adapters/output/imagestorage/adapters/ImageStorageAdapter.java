package bio.kuno.TheOne.adapters.output.imagestorage.adapters;

import bio.kuno.TheOne.adapters.input.controllers.exceptions.StorageException;
import bio.kuno.TheOne.adapters.input.controllers.exceptions.StorageFileNotFoundException;
import bio.kuno.TheOne.adapters.output.repositories.dtos.ImageDatabaseDto;
import bio.kuno.TheOne.adapters.output.repositories.mappers.ImageMapper;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.ImagePathRepository;
import bio.kuno.TheOne.config.StorageProperties;
import bio.kuno.TheOne.domain.ImageDto;
import bio.kuno.TheOne.domain.UserEntityDto;
import bio.kuno.TheOne.ports.output.imagestorage.ImageStoragePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
@Service
public class ImageStorageAdapter implements ImageStoragePort {

    private final Path rootLocation;
    @Autowired
    private ImagePathRepository imagePathRepository;
    @Autowired
    public ImageStorageAdapter(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());

    }

    @Override
    public List<String> getUrlsForImages(UserEntityDto userEntityDto) {

        return null;
    }

    @Override
    public List<ImageDto> uploadImages(List<MultipartFile> images) {
        List<ImageDto> imagePathDtos = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                if (image.isEmpty()) {
                    throw new StorageException("Failed to store empty file.");
                }
                Path destinationFile = this.rootLocation.resolve(Paths.get(image.getOriginalFilename())).normalize().toAbsolutePath();
                if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                    // This is a security check
                    throw new StorageException(
                            "Cannot store file outside current directory.");
                }
                try (InputStream inputStream = image.getInputStream()) {
                    ImageDatabaseDto imagePathDatabaseDto = new ImageDatabaseDto().builder().name(image.getOriginalFilename()).build();
                    Files.copy(inputStream, destinationFile,
                            StandardCopyOption.REPLACE_EXISTING);
                    imagePathDtos.add(ImageMapper.INSTANCE.toImagePathDto(imagePathRepository.save(imagePathDatabaseDto)));
                }
            } catch (IOException e) {
                throw new StorageException("Failed to store file.", e);
            }
        }
        return imagePathDtos;
    }

    @Override
    public byte[] getImage(String fileName) {
        try {
            Path file = load(fileName);
            File imageFile = file.toFile();
            try {

               return Files.readAllBytes(imageFile.toPath());
            }
            catch (IOException e){
                throw new StorageFileNotFoundException(
                        "Could not read file: " + imageFile);
            }
        }
        catch (Exception e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

}
