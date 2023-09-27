package bio.kuno.TheOne.config.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    @Getter
    private final HttpStatus code;
    public AppException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
