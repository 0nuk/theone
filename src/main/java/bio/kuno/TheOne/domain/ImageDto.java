package bio.kuno.TheOne.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {
    private int id;
    private String name;
    private Instant createdOn;
}
