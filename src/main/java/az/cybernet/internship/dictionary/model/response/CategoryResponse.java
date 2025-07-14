package az.cybernet.internship.dictionary.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse implements Serializable {
    static final long serialVersionUID = 1L;

    Long id;

    String name;

    String description;

    Boolean isActive;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    LocalDateTime updatedAt;

    @Builder.Default
    List<ItemResponse> items = new ArrayList<>();
}
