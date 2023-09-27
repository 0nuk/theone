package bio.kuno.TheOne.adapters.output.repositories.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "interests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestsDatabaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String interest;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdOn;
    @UpdateTimestamp
    private Instant updatedOn;
}
