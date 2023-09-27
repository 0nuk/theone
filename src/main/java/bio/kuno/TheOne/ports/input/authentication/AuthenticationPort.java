package bio.kuno.TheOne.ports.input.authentication;

import bio.kuno.TheOne.config.exceptions.AppException;
import bio.kuno.TheOne.ports.input.authentication.dtos.AuthenticationRequestDto;
import bio.kuno.TheOne.ports.input.authentication.dtos.RegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationPort {

    ResponseEntity register(RegisterRequestDto request) throws AppException;

    ResponseEntity authenticate(AuthenticationRequestDto request) throws AppException;
}
