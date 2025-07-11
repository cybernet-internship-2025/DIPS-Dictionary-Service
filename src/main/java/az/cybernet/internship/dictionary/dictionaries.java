package az.cybernet.internship.dictionary;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "dictionaries")
public class dictionaries {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer id;
    @NotEmpty(message = "Value cannot be empty")
    private String value;
    private boolean isActive;

    public dictionaries(Integer id, String value, boolean isActive) {
        this.id = id;
        this.value = value;
        this.isActive = isActive;
    }

    public dictionaries() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
