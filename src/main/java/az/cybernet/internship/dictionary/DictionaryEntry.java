package az.cybernet.internship.dictionary;

import java.time.LocalDateTime;

public class DictionaryEntry {
    private Long id;
    private String value;
    private String description;
    private Boolean isActive;
    private LocalDateTime deletedAt;

    public DictionaryEntry() {}

    public DictionaryEntry(Long id, String value, String description, Boolean is_active, LocalDateTime deletedAt) {
        this.id = id;
        this.value = value;
        this.description = description;
        this.isActive = is_active;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
