package bio.kuno.TheOne.adapters.output.repositories.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "privileges")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeDatabaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "privileges")
    private Collection<RoleDatabaseDto> roles;
}
