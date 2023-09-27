package bio.kuno.TheOne.adapters.output.repositories.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntityDatabaseDto implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String secondLastname;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "fk_user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "fk_role", referencedColumnName = "id"))
    private Collection<RoleDatabaseDto> roles;
    private Instant bornIn;
    private boolean enabled;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ImageDatabaseDto> images;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdOn;
    @UpdateTimestamp
    private Instant updatedOn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> role.getPrivileges().forEach(
                        privilege -> authorities.add(new SimpleGrantedAuthority(privilege.getName()))));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // We don't contemplate if it is expired or not
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // We don't contemplate if it is Locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // We don't contemplate if it is expired or not
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
