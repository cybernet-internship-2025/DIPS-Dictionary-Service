package az.cybernet.internship.dictionary.model.category;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {
    private UUID id;
    private String name;
    private String description;

    // OneToMany əlaqə - lazımdırsa, istifadə olunur
    //private List<Dictionary> items;
}

