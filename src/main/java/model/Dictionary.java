package model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Dictionary {
    private String id;
    private String category;
    private String value;
    private String description;
    private Boolean isActive;
    private LocalDateTime deletedAt;

}
