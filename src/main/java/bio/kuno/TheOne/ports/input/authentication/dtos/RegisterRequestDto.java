package bio.kuno.TheOne.ports.input.authentication.dtos;

import bio.kuno.TheOne.adapters.output.repositories.dtos.RoleDatabaseDto;
import bio.kuno.TheOne.domain.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private Instant bornIn;
}
