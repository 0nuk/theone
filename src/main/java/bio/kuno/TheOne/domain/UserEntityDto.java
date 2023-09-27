package bio.kuno.TheOne.domain;

import bio.kuno.TheOne.adapters.output.repositories.dtos.RoleDatabaseDto;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityDto {
    private int id;
    private String name;
    private String lastname;
    private String secondLastname;
    private String email;
    private String password;
    private Set<RoleDatabaseDto> roles;
    private Instant bornIn;
    private boolean enabled;
    private Set<ImageDto> images;
    private Instant createdOn;
    private Instant updatedOn;
}
