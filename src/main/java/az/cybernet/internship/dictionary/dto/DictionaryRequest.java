package az.cybernet.internship.dictionary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class DictionaryRequest {
    private UUID id;
    private String category;
    private String value;
    private String description;
    private Boolean isActive;
    private LocalDateTime deletedAt;

}