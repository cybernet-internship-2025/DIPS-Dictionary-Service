package az.cybernet.internship.dictionary.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {
    private UUID id;

    private String category;

    @NotBlank
    private String value;

    private String description;

    private Boolean isActive;

    private LocalDateTime deletedAt;
}
