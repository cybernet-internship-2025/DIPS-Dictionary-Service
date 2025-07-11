package az.cybernet.internship.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryEntry {
    private Long id;
    private String value;
    private String description;
    private Boolean isActive;
    private LocalDateTime deletedAt;

    public DictionaryEntry(Long id, String value, String description) {
        this(id, value, description, true, null);
    }
}
