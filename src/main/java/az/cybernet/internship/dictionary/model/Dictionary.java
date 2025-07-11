package az.cybernet.internship.dictionary.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "dictionary")
@RequiredArgsConstructor
public class Dictionary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String category;
    @NotNull
    private String value;
    private String description;

    private Boolean isActive;

    @Timestamp
    private LocalDateTime deletedAt;
}
