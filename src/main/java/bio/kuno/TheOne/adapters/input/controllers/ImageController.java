package bio.kuno.TheOne.adapters.input.controllers;


import java.util.List;

import bio.kuno.TheOne.adapters.input.controllers.exceptions.StorageFileNotFoundException;
import bio.kuno.TheOne.adapters.output.imagestorage.service.SignedLinksService;
import bio.kuno.TheOne.ports.output.imagestorage.ImageStoragePort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@Service
public class ImageController {
    @Autowired
    private ImageStoragePort imageStoragePort;

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("files") List<MultipartFile> files) {
        imageStoragePort.uploadImages(files);
        return SignedLinksService.generateSignedUrl("http://localhost:8080/api/images/", 30000, files.get(0).getOriginalFilename());
    }

    @GetMapping("/")
    public ResponseEntity<byte[]> accessImage(
            HttpServletRequest request,
            @RequestParam("expires") long expires,
            @RequestParam("parameter") String fileName,
            @RequestParam("signature") String signature
    ) {
        String baseUrl = request.getRequestURL().toString().split("\\?")[0];

        if(SignedLinksService.validateSignedUrl(baseUrl,expires,fileName,signature)) {
            try {
                byte[] imageBytes = imageStoragePort.getImage(fileName);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(imageBytes);
            } catch (Exception e) {
                System.out.println(e);
                return ResponseEntity.status(500).body(null);
            }
        } else {
            System.out.println("not valid");
            return ResponseEntity.status(500).body(null);
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }


}
