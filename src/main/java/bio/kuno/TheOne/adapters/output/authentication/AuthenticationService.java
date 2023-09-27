package bio.kuno.TheOne.adapters.output.authentication;

import bio.kuno.TheOne.ports.input.authentication.dtos.AuthenticationRequestDto;
import bio.kuno.TheOne.ports.input.authentication.dtos.AuthenticationResponseDto;
import bio.kuno.TheOne.ports.input.authentication.dtos.RegisterRequestDto;
import bio.kuno.TheOne.adapters.output.repositories.dtos.UserEntityDatabaseDto;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.RoleRepository;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.UserEntityRepository;
import bio.kuno.TheOne.config.exceptions.AppException;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponseDto register(RegisterRequestDto request) throws AppException {
        Optional<UserEntityDatabaseDto> optionalUser = repository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new AppException("Email alredy exists", HttpStatus.BAD_REQUEST);
        }
        UserEntityDatabaseDto user = UserEntityDatabaseDto.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .roles(List.of(roleRepository.findByName("ROLE_USER")))
                .enabled(true)
                .build();
        repository.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRoles());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserEntityDatabaseDto user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));//Exception will never been thrown because of authenticationManager.authenticate
        String jwtToken = jwtService.generateToken(user);
        System.out.println(jwtToken);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
