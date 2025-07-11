package az.cybernet.internship.dictionary.dao.entity;


import az.cybernet.internship.dictionary.model.enums.DictionaryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "dictionary")
@EqualsAndHashCode(of = "id")
public class DictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String value;
    private String description;
    private LocalDateTime deletedAt;
    @Enumerated(EnumType.STRING)
    private DictionaryStatus status;


}
