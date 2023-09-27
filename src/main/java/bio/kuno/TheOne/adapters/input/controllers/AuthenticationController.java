package bio.kuno.TheOne.adapters.input.controllers;

import bio.kuno.TheOne.ports.input.authentication.dtos.AuthenticationRequestDto;
import bio.kuno.TheOne.ports.input.authentication.dtos.AuthenticationResponseDto;
import bio.kuno.TheOne.ports.input.authentication.dtos.RegisterRequestDto;
import bio.kuno.TheOne.adapters.output.authentication.AuthenticationService;
import bio.kuno.TheOne.ports.input.authentication.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Service
public class AuthenticationController implements AuthenticationPort {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody RegisterRequestDto request
    ) {
        AuthenticationResponseDto userDto = authenticationService.register(request);
        return ResponseEntity.ok(userDto);
        // return ResponseEntity.created(URI.create("/users/" + userDto.getId())).body(userDto); // 201
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequestDto request
    ) {
        AuthenticationResponseDto userDto = authenticationService.authenticate(request);
        return ResponseEntity.ok(userDto);
    }
}
