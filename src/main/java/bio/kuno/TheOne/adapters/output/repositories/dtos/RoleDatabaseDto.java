package bio.kuno.TheOne.adapters.output.repositories.dtos;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "roles")
public class RoleDatabaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonBackReference
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntityDatabaseDto> users;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "privilege_id", referencedColumnName = "id"))
    private Collection<PrivilegeDatabaseDto> privileges;
}
