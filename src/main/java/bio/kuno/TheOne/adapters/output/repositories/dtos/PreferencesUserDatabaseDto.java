package bio.kuno.TheOne.adapters.output.repositories.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;
@Entity
@Table(name = "users_preference")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferencesUserDatabaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant minBornIn;
    private Instant maxBornIn;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_preference_interests", joinColumns = @JoinColumn(name = "fk_user_preference", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "fk_interest", referencedColumnName = "id"))
    private Set<InterestsDatabaseDto> interests;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdOn;
    @UpdateTimestamp
    private Instant updatedOn;
}
